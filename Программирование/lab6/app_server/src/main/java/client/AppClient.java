package client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.DatagramChannel;
import java.util.NoSuchElementException;
import java.util.Scanner;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import commands.Command;
import exceptions.ArgumentException;
import exceptions.CommandNotFoundException;
import exceptions.TimeOutException;
import server.UDPMessage;
import server.UDPReadWrite;

//Приём подключений
public class AppClient {
    private static final int BUFFER_SIZE = 1024;
    private static final InetSocketAddress SERVER = new InetSocketAddress("127.0.0.1", 5000);

    public static void main(String[] args) {
        //Сделать обработку NoSuchElememntException
        Scanner scanner = new Scanner(System.in);

        DatagramChannel channel = null;
        try {
            channel = DatagramChannel.open();
            channel.configureBlocking(false);
            UDPReadWrite udprw = new UDPReadWrite(channel, BUFFER_SIZE);

            while(true) {
                try {
                    System.out.print(">> ");
                    String line = scanner.nextLine();
                    
                    Command command = Command.fromConsole(line.split(" "), System.out, scanner);
                    udprw.write(new UDPMessage(SERVER, command.serialize()));
                    
                    UDPMessage response = udprw.read(5000);
                    JsonObject object = JsonParser.parseString(response.body).getAsJsonObject();
                    String text = object.get("text").getAsString();
                    System.out.println(text);
                } catch(CommandNotFoundException exception) {
                    System.out.println("Команда не найдена!");
                } catch(ArgumentException exception) {
                    System.out.println(exception.getMessage());
                } catch(TimeOutException exception) {
                    System.out.println("Превышено время ожидания ответа от сервера");
                } 
            }
        } catch(InterruptedException e) {
            System.out.println("Выход!");
            scanner.close();
            if (channel != null)
                try {
                    channel.close();
                } catch (IOException e1) {
                    //
                }
            System.exit(0);
        } catch(NoSuchElementException e) {
            System.out.println("\nВыход!");
            scanner.close();
            if (channel != null)
                try {
                    channel.close();
                } catch (IOException e1) {
                    //
                }
            System.exit(0);
        }catch(IOException e){
            scanner.close();
            if (channel != null)
                try {
                    channel.close();
                } catch (IOException e1) {
                    //
                }
            e.printStackTrace();
            System.exit(1);
        }
    }
}