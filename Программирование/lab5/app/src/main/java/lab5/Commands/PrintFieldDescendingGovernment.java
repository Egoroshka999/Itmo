package lab5.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import lab5.Collections.City;
import lab5.Collections.Government;

/**
 * ����� ������� print_field_descending_government
 *
 * @author ��������� ����, P3115
 */
public class PrintFieldDescendingGovernment extends AbstractCommand{
    public PrintFieldDescendingGovernment(){
        name = "print_field_descending_government";
        help = "������� �������� ���� government ���� ��������� � ������� ��������";
    }

    /**
     * ������� �������� ���� government ���� ��������� � ������� ��������
     *
     * @param args              �� ��������� ���������
     * @param treeSet           ���������, � ������� �������� ������������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("������� �� ��������� ���������");
        else{
            try {
                if (treeSet.size() > 0) {
                    ArrayList<Integer> governmentID = new ArrayList<Integer>(treeSet.size());
                    for (City city : treeSet) {
                        governmentID.add(city.getGovernmentID());
                    }
                    Collections.sort(governmentID, Collections.reverseOrder());
                   System.out.println("�������� government ���� ��������� � ������� ��������: ");
                    for (int x: governmentID){
                        System.out.println(Government.values()[x].name());
                    }                    
                } else System.out.println("������ ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
