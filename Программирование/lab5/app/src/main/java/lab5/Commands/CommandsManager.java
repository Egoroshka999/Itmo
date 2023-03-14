package lab5.Commands;

import lab5.Collections.City;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * Класс управляющей выбором комманд
 * 
 * @author Деревягин Егор, P3115
 */
public class CommandsManager {
    private static HashSet<AbstractCommand> commands = new HashSet<>();
    private static CommandsManager commandsManager = new CommandsManager();

    /**
     * Конструктор при вызове которого в HashSey commands будут добавлены все доступные команды
     */
    private CommandsManager() {
        commands.add(new Add());
        commands.add(new Help());
        commands.add(new Info());
        commands.add(new Show());        
        commands.add(new UpdateId());
        commands.add(new RemoveByID());
        commands.add(new AddIfMin());
        commands.add(new Clear());
        commands.add(new Save());
        commands.add(new ExecuteScript());
        commands.add(new Exit());
        commands.add(new RemoveLower());
        commands.add(new History());
        commands.add(new SumOfMetersAboveSeaLevel());
        commands.add(new MinByGovernment());
        commands.add(new PrintFieldDescendingGovernment());
    }

    private boolean exist = false;
    private boolean notPrintAdd = false;
    private String scriptFileName;
    private boolean isScript = false;
    private BufferedReader scriptBufferedReader;
    private ArrayList<String> history = new ArrayList<String>();

    /**
     * Определяет, какую команду ввёл полльзователь и исполняет её
     * 
     * @param args      команда с аргументами
     * @param treeSet   коллекция, с которой работает пользователь
     */
    public static void ExecuteCommand(String[] args, TreeSet<City> treeSet) {
        String cmd = args[0].trim();
        args = Arrays.copyOfRange(args, 1, args.length);
        boolean exist = false;
        for (AbstractCommand command : commands)
            if (command.getName().equals(cmd)) {
                command.execute(args, treeSet, commandsManager);
                exist = true;
                commandsManager.history.add(command.getName());
                if (commandsManager.history.size() == 9) {
                    commandsManager.history.remove(0);
                }
            }
        if (!exist) System.out.println("Команда не найдена. Для просмотра доступных команд введите help");
    }


    /**
     * Проматывает строки в случае ошибки при считывании команды из скрипта
     */
    public void commandRewider() {
        try {
            for (int i = 1; i < 10; i++) scriptBufferedReader.readLine();
        } catch (Exception ignored) {
        }
    }

    /**
     * @return HashSey с командами
     */
    public static HashSet<AbstractCommand> getCommands() {
        return commands;
    }

    /**
     * @param script работает в данный момент пользователь со скриптом или нет
     */
    public void setScript(boolean script) {
        isScript = script;
    }

    /**
     * @return работает в данный момент пользователь со скриптом или нет
     */
    public boolean isScript() {
        return isScript;
    }

    /**
     * @return cтоит ли оповещать пользователя о добавлении объекта
     */
    public boolean isNotPrintAdd() {
        return notPrintAdd;
    }

    /**
     * @param notPrintAdd стоит ли оповещать пользователя о добавлении объекта
     */
    public void setNotPrintAdd(boolean notPrintAdd) {
        this.notPrintAdd = notPrintAdd;
    }

    /**
     * @return существование элемента коллекции
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * @param exist существование элемента коллекции
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }

    /**
     * @return имя скрипта
     */
    public String getScriptFileName() {
        return scriptFileName;
    }

    /**
     * @param scriptFileName имя скрипта
     */
    public void setScriptFileName(String scriptFileName) {
        this.scriptFileName = scriptFileName;
    }

    /**
     * @return считыватель скрипта
     */
    public BufferedReader getScriptBufferedReader() {
        return scriptBufferedReader;
    }

    /**
     * @param scriptBufferedReader считыватель скрипта
     */
    public void setScriptBufferedReader(BufferedReader scriptBufferedReader) {
        this.scriptBufferedReader = scriptBufferedReader;
    }

    /**
     * @return история комманд
     */
    public String[] getHistory() {
        return history.toArray(new String[]{});
    }

}
