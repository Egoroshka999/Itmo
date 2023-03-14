package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;


/**
 * ����� ������� exit
 * 
 * @author ��������� ����, P3115
 */
public class Exit extends AbstractCommand{
    public Exit() {
        name = "exit";
        help = "��������� ��������� (��� ���������� � ����)";
    }

    /**
     * ��������� ������ � ���������, ������� ��� ����������
     *
     * @param args            �� ��������� ���������
     * @param treeSet   ���������, � ������� �������� ������������
     * @param commandsManager ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("������� �� ��������� ���������");
        else {
            System.exit(0);
        }
    }
}
