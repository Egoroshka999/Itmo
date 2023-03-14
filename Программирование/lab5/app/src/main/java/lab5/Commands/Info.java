package lab5.Commands;

import java.util.TreeSet;

import lab5.App;
import lab5.Collections.City;

/**
 * Класс команды info
 *
 * @author Деревягин Егор, P3115
 */
public class Info extends AbstractCommand{

    public Info() {
        name = "info";
        help = "вывести в стандартный поток вывода информацию о коллекции";
    }

    /**
     * Выводит инормацию о коллекции
     *
     * @param args              не принимает аргументов
     * @param treeSet           коллекция, с которой работает пользователь
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("Команда не принимает аргументы");
        else {
            System.out.println("Тип коллекции: " + treeSet.getClass().getName());
            System.out.println("Время создания коллекции: " + App.creationDate);
            System.out.println("Количество элементов в коллеции: " + treeSet.size());
        }
        
    }
    
}
