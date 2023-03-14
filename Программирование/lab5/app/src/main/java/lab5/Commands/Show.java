package lab5.Commands;

import java.util.Arrays;
import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� show
 *
 * @author ��������� ����, P3115
 */
public class Show extends AbstractCommand{
    public Show() {
        name = "show";
        help = "������� � ����������� ����� ������ ��� �������� ��������� � ��������� �������������";
    }
    /**
     * ���������� �������� ���������
     *
     * @param args            �� ��������� ����������
     * @param treeSet         ���������, ������� ����� ��������
     * @param commandsManager ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("������� �� ��������� ���������");
        } else System.out.println(Arrays.toString(treeSet.toArray()));
    }
    
}
