package lab5.Commands;

import java.util.TreeSet;

import lab5.App;
import lab5.Collections.City;

/**
 * ����� ������� info
 *
 * @author ��������� ����, P3115
 */
public class Info extends AbstractCommand{

    public Info() {
        name = "info";
        help = "������� � ����������� ����� ������ ���������� � ���������";
    }

    /**
     * ������� ��������� � ���������
     *
     * @param args              �� ��������� ����������
     * @param treeSet           ���������, � ������� �������� ������������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("������� �� ��������� ���������");
        else {
            System.out.println("��� ���������: " + treeSet.getClass().getName());
            System.out.println("����� �������� ���������: " + App.creationDate);
            System.out.println("���������� ��������� � ��������: " + treeSet.size());
        }
        
    }
    
}
