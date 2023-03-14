package lab5.Commands;

import java.util.Arrays;
import java.util.TreeSet;

import lab5.Collections.City;

/**
 * Класс команды show
 *
 * @author Деревягин Егор, P3115
 */
public class Show extends AbstractCommand{
    public Show() {
        name = "show";
        help = "вывести в стандартный поток вывода все элементы коллекции в строковом представлении";
    }
    /**
     * Показывает элементы коллекции
     *
     * @param args            не принимает аргументов
     * @param treeSet         коллекция, которую нужно показать
     * @param commandsManager объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("Команда не принимает аргументы");
        } else System.out.println(Arrays.toString(treeSet.toArray()));
    }
    
}
