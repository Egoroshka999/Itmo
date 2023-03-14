package lab5.Commands;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashSet;
import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� execute_script
 * 
 * @author ��������� ����, P3115
 */
public class ExecuteScript extends AbstractCommand{
    HashSet<String> scriptsNames = new HashSet<>();

    public ExecuteScript() {
        name = "execute_script";
        help = "��������� ����������� ������";
    }

    /**
     * ��������� ��������� ������
     * @param args              ��� �������
     * @param treeSet           ���������, � ������� �������� ������������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length != 1) System.out.println("������� ��������� ���� ���� ��������");
        else {
            commandsManager.setScript(true);
            try {
                String sfn = args[0];
                commandsManager.setScriptFileName(sfn);
                commandsManager.setScriptBufferedReader(new BufferedReader(new FileReader(commandsManager.getScriptFileName())));
                String line = "";
                while (true) {
                    line = commandsManager.getScriptBufferedReader().readLine();
                    String[] nargs = line.split(" ");
                    if (nargs[0].equals("execute_script")) {
                        if (!scriptsNames.contains(nargs[1])) {
                            scriptsNames.add(sfn);
                            CommandsManager.ExecuteCommand(nargs, treeSet);
                        } else {
                            System.out.println("�� �� ������ ��������� ������� � ����������� �������, ������� �������� ����������� ������, ���������� ������� ������ ������� ������������ �������, ������� ��� ���������� �����");
                            System.out.println("�� ������� ���������: execute_script " + sfn + ". ���������� �������: " + scriptsNames);
                        }
                    }
                    scriptsNames.add(sfn);
                    if (!nargs[0].equals("execute_script")) {
                        CommandsManager.ExecuteCommand(nargs, treeSet);
                    }
                }
            } catch (NullPointerException ignored) {
            } catch (FileNotFoundException e) {
                System.out.println("���� �� ������");

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        commandsManager.setScript(false);
        scriptsNames.clear();
    }
}
