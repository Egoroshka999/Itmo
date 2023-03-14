package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;
import lab5.Commands.CommandsManager;

/**
 * Класс команды history
 * 
 * @author Деревягин Егор, P3115
 */
public class History extends AbstractCommand{
    public History() {
        name = "history";
        help = "выводит последние 8 команд";
    }

    /**
     * Выводит историю по 8 последним командам
     *
     * @param args              не принимает аргументов
     * @param treeSet           коллекция, с которой работает пользователь
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("На данном этапе команда не принимает аргументы");
            commandsManager.commandRewider();
        } else {
            System.out.println("[");
            for (String command: commandsManager.getHistory()) {
                System.out.println(command); 
            }
            System.out.println("]");
        }
    }

    
}
