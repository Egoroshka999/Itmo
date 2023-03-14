package lab5.Commands;

import lab5.Collections.*;
import lab5.Commands.CommandsManager;

import java.util.TreeSet;

/**
 * ����� ������� update_id
 *
 * @author ��������� ����, P3115
 */
public class UpdateId extends AbstractCommand{
    public UpdateId() {
        name = "update_id";
        help = "��������� �������� �������� ���������, id �������� ����� ���������";


    }

    /**
     * ��������� ������� ��������� �� id
     *
     * @param args            id
     * @param treeSet         ���������, ������� ������� ����� ��������
     * @param commandsManager ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        try {
            if (args.length != 1) {
                System.out.println("������� ��������� ���� ���� ��������");
                if (commandsManager.isScript()) {
                    commandsManager.commandRewider();
                }
            } else {
                long oldIdSetter = City.idSetter;
                commandsManager.setNotPrintAdd(true);
                City.idSetter = Integer.parseInt(args[0]);
                String[] nargs = new String[]{"remove_by_id", args[0]};
                CommandsManager.ExecuteCommand(nargs, treeSet);
                if (commandsManager.isExist()) {
                    nargs = new String[]{"add"};
                    CommandsManager.ExecuteCommand(nargs, treeSet);
                    System.out.println("������� � id = " + (City.idSetter - 1) + " �������");
                }
                City.idSetter = oldIdSetter;
            }
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            System.out.println("�������� ��� ���������");
            if (commandsManager.isScript()) {
                commandsManager.commandRewider();
            }
        }
        commandsManager.setNotPrintAdd(false);
    }
    
}
