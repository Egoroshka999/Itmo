package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� sum_of_meters_above_sea_level
 *
 * @author ��������� ����, P3115
 */
public class SumOfMetersAboveSeaLevel extends AbstractCommand{
    public SumOfMetersAboveSeaLevel(){
        name = "sum_of_meters_above_sea_level";
        help = "������� ����� �������� ���� metersAboveSeaLevel ��� ���� ��������� ���������";
    } 
    
    /**
     * ������� ����� �������� ���� metersAboveSeaLevel ��� ���� ��������� ���������
     *
     * @param args            �� ��������� ����������
     * @param treeSet         ���������, ������� ������� ����� ��������
     * @param commandsManager ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("�� ������ ����� ������� �� ��������� ���������");
            commandsManager.commandRewider();
        } else {
            System.out.println(treeSet.stream().mapToDouble((t) -> t.getMetersAboveSeaLevel()).sum());
        }
        
    }
    
}
