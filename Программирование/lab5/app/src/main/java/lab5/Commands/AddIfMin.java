package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

import java.util.Arrays;

/**
 * ����� ������� add_if_min
 * 
 * @author ��������� ����, P3115
 */
public class AddIfMin  extends AbstractCommand{
    public AddIfMin() {
        name = "add_if_min";
        help = "��������� ����� ������� � ���������, ���� ��� �������� ������, ��� � ����������� �������� ���� ���������";
    }
    

    /**
     * ��������� ����� ������� � ���������
     * 
     * @param args              �� ��������� ���������
     * @param treeSet           ���������, � ������� ����������� �������
     * @param commandsManager   ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("�� ������ ����� ������� �� ��������� ���������");
            commandsManager.commandRewider();
        } else {
            commandsManager.setNotPrintAdd(true);
            try {
                String[] nargs = new String[]{"add"};
                CommandsManager.ExecuteCommand(nargs, treeSet);
                City last = treeSet.last();
                City[] arr = treeSet.toArray(new City[0]);
                if (Arrays.stream(arr).anyMatch((t) -> t.getArea() < last.getArea() )) {
                    treeSet.removeIf(city -> city.getId() == last.getId());
                    City.idSetter--;
                    System.out.println("������� �� ��������, ��� ��� �� �������� �����������");
                } else System.out.println("������� �������� � ���������");
            } catch (NullPointerException ignored) {
            } catch (Exception e) {
                System.out.println("�������� ��� ���������");
                if (commandsManager.isScript()) {
                    commandsManager.commandRewider();
                }
            }
        }
        commandsManager.setNotPrintAdd(false);
    }
}
