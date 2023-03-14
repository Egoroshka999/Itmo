package lab5.Commands;

import lab5.Collections.City;

import java.util.TreeSet;

/**
 * ����� ������� help
 * 
 * @author ��������� ����, P3115
 */
public class Help extends AbstractCommand {
    public Help() {
        name = "help";
        help = "������� ������� �� ��������� ��������";
    }

    /**
     * ������� ������� �� ��������
     *
     * @param args            �� ��������� ����������
     * @param treeSet   ���������, � ������� �������� ������������
     * @param commandsManager ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0){
            System.out.println("������� �� ��������� ���������");
        } else
            for (AbstractCommand command : commandsManager.getCommands())
                System.out.println("������� " + command.getName() + ": " + command.getHelp());
    }
}