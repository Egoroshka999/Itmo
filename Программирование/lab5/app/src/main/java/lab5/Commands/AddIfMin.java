package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

import java.util.Arrays;

/**
 * Класс команды add_if_min
 * 
 * @author Деревягин Егор, P3115
 */
public class AddIfMin  extends AbstractCommand{
    public AddIfMin() {
        name = "add_if_min";
        help = "добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции";
    }
    

    /**
     * Добавляет новый элемент в коллекцию
     * 
     * @param args              не принимает аргументы
     * @param treeSet           коллекция, в которую добавляется элемент
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("На данном этапе команда не принимает аргументы");
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
                    System.out.println("Элемент не добавлен, так как не является минимальным");
                } else System.out.println("Элемент добавлен в коллекцию");
            } catch (NullPointerException ignored) {
            } catch (Exception e) {
                System.out.println("Неверный тип аргумента");
                if (commandsManager.isScript()) {
                    commandsManager.commandRewider();
                }
            }
        }
        commandsManager.setNotPrintAdd(false);
    }
}
