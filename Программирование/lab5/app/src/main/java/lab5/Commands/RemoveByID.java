package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� remove_by_id
 *
 * @author ��������� ����, P3115
 */
public class RemoveByID extends AbstractCommand{
    public RemoveByID(){
        name = "remove_by_id";
        help = "������� ������� �� ��������� �� ��� id";
    }

     /**
     * ������� ������� �� id
     *
     * @param args              id
     * @param treeSet           ���������, �� ������� ��������� �������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length != 1) {
            System.out.println("������� ��������� ���� ���� ��������");
        } else {
            try {
                int id = Integer.parseInt(args[0]);
                commandsManager.setExist(false);
                for (City city : treeSet)
                    if (city.getId() == id) {
                        commandsManager.setExist(true);
                        break;
                    }
                treeSet.removeIf(spaceMarine -> spaceMarine.getId() == id);
                if (!commandsManager.isExist()) {
                    System.out.println("������� ��������� � id = " + args[0] + " �� ������");
                } else if (!commandsManager.isNotPrintAdd())
                    System.out.println("������� ��������� � id = " + args[0] + " �����");

            } catch (Exception e) {
                System.out.println("�������� ��� ���������");
            }
        }
    }
    
}
