package server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import collection.MyTreeSet;
import commands.*;
import exceptions.CommandNotFoundException;
import exceptions.DatabaseAccessException;
import exceptions.DatabaseParseException;
import exceptions.TimeOutException;

public class AppServerNonBlocking {
    private static final int BUFFER_SIZE = 1024;
    private static final int PORT = 5000;
    private static final Logger logger = LogManager.getLogger(AppServerNonBlocking.class);
    public static void main(String[] args) {
        Help.registerEntry(Add.getHelpEntry());
        Help.registerEntry(AddIfMin.getHelpEntry());
        Help.registerEntry(Clear.getHelpEntry());
        Help.registerEntry(Help.getHelpEntry());
        Help.registerEntry(History.getHelpEntry());
        Help.registerEntry(Info.getHelpEntry());
        Help.registerEntry(MinByGovernment.getHelpEntry());
        Help.registerEntry(PrintFieldDescendingGovernment.getHelpEntry());
        Help.registerEntry(RemoveByID.getHelpEntry());
        Help.registerEntry(RemoveLower.getHelpEntry());
        Help.registerEntry(Show.getHelpEntry());
        Help.registerEntry(SumOfMetersAboveSeaLevel.getHelpEntry());
        Help.registerEntry(UpdateID.getHelpEntry());
        Help.registerEntry(ExecuteScript.getHelpEntry());

        MyTreeSet treeSet = null; 
        if (args.length > 1) {
            logger.error("Принимает только один аргумент на вход");
            System.exit(1);
        }
        if (args.length > 0) {
            try {
                logger.info("Попытка загрузить файл " + args[0]);
                treeSet = load(args[0]);    
            } catch (DatabaseAccessException e) {
               logger.error("Нету доступа к файлу", e); 
               System.exit(1);
            } catch (DatabaseParseException e) {
                logger.error("Неверный формат базы данных", e);
                System.exit(1);
            }
        } else {
            try {
                logger.info("Попытка загрузить файл " + "file.json");
                treeSet = load("file.json");    
            } catch (DatabaseAccessException e) {
               logger.error("Нету доступа к файлу", e); 
               System.exit(1);
            } catch (DatabaseParseException e) {
                logger.error("Неверный формат базы данных", e);
                System.exit(1);
            }    
        }
        

        try {
            DatagramChannel channel = DatagramChannel.open();
            channel.configureBlocking(false);
            channel.bind(new InetSocketAddress(PORT));
            UDPReadWrite udprw = new UDPReadWrite(channel, BUFFER_SIZE);

            while(true) {
                UDPMessage request = udprw.read(0);
                logger.info("Получен запрос от " + request.addr);
                logger.debug("Тело запроса: " + request.body);
                try {
                    logger.info("Попытка десериализовать команду");
                    Command cmd = Command.fromJson(request.body);
                    logger.info("Десериализована команда: " + cmd.getClass().getName());
                    String output = cmd.execute(treeSet);
                    logger.debug("Команда выполнена, вывод: " + output);
                    History.push(cmd.getClass().getSimpleName().toLowerCase());
                    
                    JsonObject object = new JsonObject();
                    object.addProperty("text", output);
                    logger.debug("Ответ сериализован: " + object.toString());
                    udprw.write(new UDPMessage(request.addr, object.toString()));
                    
                    logger.info("Попытка сохранить базу данных");
                    save("obosralsya.json", treeSet);
                } catch(CommandNotFoundException e) {
                    logger.warn("Запрошенная команда не найдена");
                    JsonObject object = new JsonObject();
                    object.addProperty("text", "Команда не найдена!");
                    udprw.write(new UDPMessage(request.addr, object.toString()));
                } 
            }
        } catch (IOException e) {
            logger.error(e);
            System.exit(1);
        } catch (TimeOutException e) {
            //Не может произойти так как TimeOut отключен
        } catch (InterruptedException e) {
            logger.info("Запрошен выход");
            try {
                logger.info("Попытка сохранить базу данных");
                save("obosralsya.json", treeSet);
                System.exit(0);
            } catch (DatabaseAccessException e1) {
                logger.error("Нету доступа к файлу", e); 
                System.exit(1);
            } 
        } catch (DatabaseAccessException e) {
            logger.error("Нету доступа к файлу", e); 
            System.exit(1);
        } 
    }

    public static MyTreeSet load(String fileName) throws DatabaseAccessException, DatabaseParseException{
        logger.info("Проверка существования файла " + fileName);
        if (!Files.exists(Paths.get(fileName)))
            return new MyTreeSet();

        try {
            String data = Files.readString(Paths.get(fileName), StandardCharsets.UTF_8);
            return new MyTreeSet(data);
        } catch (IOException e) {
            throw new DatabaseAccessException(e.getMessage());
        } catch (JsonSyntaxException e) {
            throw new DatabaseParseException(e.getMessage());
        } catch (JsonParseException e) {
            throw new DatabaseParseException(e.getMessage());
        }
    }

    public static void save(String filename, MyTreeSet treeSet) throws DatabaseAccessException {
        try {
            String data = treeSet.serialize();
            Files.write(Paths.get(filename), data.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            throw new DatabaseAccessException(e.getMessage());
        }
    }
}
