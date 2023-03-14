package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * ����� ������� remove_lower
 *
 * @author ��������� ����, P3115
 */
public class RemoveLower extends AbstractCommand{
    public RemoveLower() {
        name = "remove_lower";
        help = "������� �� ��������� ��� ��������, ������� ��� ��������";
    }

    /**
     * ������� �� ��������� ��� ��������, ������� ��� ��������
     *
     * @param args              �� ��������� ���������
     * @param treeSet           ���������, �� ������� ��������� ��������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("�� ������ ����� ������� �� ��������� ���������");
            commandsManager.commandRewider();
        } else {
            try {
                if (treeSet.size() > 0) {
                    commandsManager.setNotPrintAdd(true);
                    String[] nargs = new String[]{"add"};
                    CommandsManager.ExecuteCommand(nargs, treeSet);
                    City added = null;
                    for (City each : treeSet) {
                        if (each.getId() == City.idSetter - 1)
                            added = each;
                    }
                    int area = added.getArea();
                    treeSet.removeIf(city -> city.getArea() < area);
                    treeSet.remove(added);
                    City.idSetter--;
                    System.out.println("�������� �������, ��� �������� ������� �� ���������");
                } else System.out.println("������ ����");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        commandsManager.setNotPrintAdd(false);
    }
    

    
}
