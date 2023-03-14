package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� clear
 * 
 * @author ��������� ����, P3115
 */
public class Clear extends AbstractCommand{
    public Clear() {
        name = "clear";
        help = "������� ���������";
    }
    
    /**
     * ������� ��� �������� ���������
     * 
     * @param args              �� ��������� ���������
     * @param treeSet           ���������, � ������� ����������� �������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("������� �� ��������� ���������");
        else {
            treeSet.clear();
            City.idSetter = 1;
            System.out.println("��������� �������");
        }
    }
}
