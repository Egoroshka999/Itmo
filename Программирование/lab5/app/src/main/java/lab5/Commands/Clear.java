package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * Класс команды clear
 * 
 * @author Деревягин Егор, P3115
 */
public class Clear extends AbstractCommand{
    public Clear() {
        name = "clear";
        help = "очищает коллекцию";
    }
    
    /**
     * Удаляет все элементы коллекции
     * 
     * @param args              не принимает аргументы
     * @param treeSet           коллекция, в которую добавляется элемент
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("Команда не принимает аргументы");
        else {
            treeSet.clear();
            City.idSetter = 1;
            System.out.println("Коллекция очищена");
        }
    }
}
