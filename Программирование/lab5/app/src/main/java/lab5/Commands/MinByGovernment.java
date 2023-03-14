package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� min_by_government
 *
 * @author ��������� ����, P3115
 */
public class MinByGovernment extends AbstractCommand{
    public MinByGovernment(){
        name = "min_by_government";
        help = "������� ���� ������ �� ���������, �������� ���� government �������� �������� �����������";
    }


    /**
     * ������� ������� ��������� � ����������� government
     *
     * @param args              �� ��������� ����������
     * @param treeSet           ���������, � ������� �������� ������������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("������� �� ��������� ���������");
        else{
            try {
                if (treeSet.size() > 0) {
                    int min = Integer.MAX_VALUE;
                    City minCity = null;
                    for (City city : treeSet) {
                        if (city.getGovernmentID() < min) {
                            min = city.getGovernmentID();
                            minCity = city;
                        }
                    }
                    System.out.println("����������� ������� �� ���� government: " + minCity);
                } else System.out.println("������ ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
