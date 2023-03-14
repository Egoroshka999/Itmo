package lab5.Commands;

import lab5.Collections.City;

import java.io.BufferedReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.TreeSet;

/**
 * ����� ����������� ������� �������
 * 
 * @author ��������� ����, P3115
 */
public class CommandsManager {
    private static HashSet<AbstractCommand> commands = new HashSet<>();
    private static CommandsManager commandsManager = new CommandsManager();

    /**
     * ����������� ��� ������ �������� � HashSey commands ����� ��������� ��� ��������� �������
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
     * ����������, ����� ������� ��� ������������� � ��������� �
     * 
     * @param args      ������� � �����������
     * @param treeSet   ���������, � ������� �������� ������������
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
        if (!exist) System.out.println("������� �� �������. ��� ��������� ��������� ������ ������� help");
    }


    /**
     * ����������� ������ � ������ ������ ��� ���������� ������� �� �������
     */
    public void commandRewider() {
        try {
            for (int i = 1; i < 10; i++) scriptBufferedReader.readLine();
        } catch (Exception ignored) {
        }
    }

    /**
     * @return HashSey � ���������
     */
    public static HashSet<AbstractCommand> getCommands() {
        return commands;
    }

    /**
     * @param script �������� � ������ ������ ������������ �� �������� ��� ���
     */
    public void setScript(boolean script) {
        isScript = script;
    }

    /**
     * @return �������� � ������ ������ ������������ �� �������� ��� ���
     */
    public boolean isScript() {
        return isScript;
    }

    /**
     * @return c���� �� ��������� ������������ � ���������� �������
     */
    public boolean isNotPrintAdd() {
        return notPrintAdd;
    }

    /**
     * @param notPrintAdd ����� �� ��������� ������������ � ���������� �������
     */
    public void setNotPrintAdd(boolean notPrintAdd) {
        this.notPrintAdd = notPrintAdd;
    }

    /**
     * @return ������������� �������� ���������
     */
    public boolean isExist() {
        return exist;
    }

    /**
     * @param exist ������������� �������� ���������
     */
    public void setExist(boolean exist) {
        this.exist = exist;
    }

    /**
     * @return ��� �������
     */
    public String getScriptFileName() {
        return scriptFileName;
    }

    /**
     * @param scriptFileName ��� �������
     */
    public void setScriptFileName(String scriptFileName) {
        this.scriptFileName = scriptFileName;
    }

    /**
     * @return ����������� �������
     */
    public BufferedReader getScriptBufferedReader() {
        return scriptBufferedReader;
    }

    /**
     * @param scriptBufferedReader ����������� �������
     */
    public void setScriptBufferedReader(BufferedReader scriptBufferedReader) {
        this.scriptBufferedReader = scriptBufferedReader;
    }

    /**
     * @return ������� �������
     */
    public String[] getHistory() {
        return history.toArray(new String[]{});
    }

}
