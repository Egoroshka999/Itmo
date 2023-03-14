package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;
import lab5.Commands.CommandsManager;

/**
 * ����� ������� history
 * 
 * @author ��������� ����, P3115
 */
public class History extends AbstractCommand{
    public History() {
        name = "history";
        help = "������� ��������� 8 ������";
    }

    /**
     * ������� ������� �� 8 ��������� ��������
     *
     * @param args              �� ��������� ����������
     * @param treeSet           ���������, � ������� �������� ������������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("�� ������ ����� ������� �� ��������� ���������");
            commandsManager.commandRewider();
        } else {
            System.out.println("[");
            for (String command: commandsManager.getHistory()) {
                System.out.println(command); 
            }
            System.out.println("]");
        }
    }

    
}
