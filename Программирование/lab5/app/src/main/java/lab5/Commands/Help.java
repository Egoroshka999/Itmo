package lab5.Commands;

import lab5.Collections.City;

import java.util.TreeSet;

/**
 * Класс команды help
 * 
 * @author Деревягин Егор, P3115
 */
public class Help extends AbstractCommand {
    public Help() {
        name = "help";
        help = "выводит справку по доступным командам";
    }

    /**
     * Выводит справку по командам
     *
     * @param args            не принимает аргументов
     * @param treeSet   коллекция, с которой работает пользователь
     * @param commandsManager объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0){
            System.out.println("Команда не принимает аргументы");
        } else
            for (AbstractCommand command : commandsManager.getCommands())
                System.out.println("Команда " + command.getName() + ": " + command.getHelp());
    }
}