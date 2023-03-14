package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * Класс команды remove_by_id
 *
 * @author Деревягин Егор, P3115
 */
public class RemoveByID extends AbstractCommand{
    public RemoveByID(){
        name = "remove_by_id";
        help = "удаляет элемент из коллекции по его id";
    }

     /**
     * Удаляет элемент по id
     *
     * @param args              id
     * @param treeSet           коллекция, из которой удаляется элемент
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length != 1) {
            System.out.println("Команда принимает лишь один аргумент");
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
                    System.out.println("Элемент коллекции с id = " + args[0] + " не найден");
                } else if (!commandsManager.isNotPrintAdd())
                    System.out.println("Элемент коллекции с id = " + args[0] + " удалён");

            } catch (Exception e) {
                System.out.println("Неверный тип аргумента");
            }
        }
    }
    
}
