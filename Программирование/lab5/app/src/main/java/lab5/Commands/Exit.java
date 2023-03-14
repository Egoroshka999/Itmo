package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;


/**
 * Класс команды exit
 * 
 * @author Деревягин Егор, P3115
 */
public class Exit extends AbstractCommand{
    public Exit() {
        name = "exit";
        help = "завершает программу (без сохранения в файл)";
    }

    /**
     * Завершает работу с коллекций, выходит без сохранения
     *
     * @param args            не принимает аргументы
     * @param treeSet   коллекция, с которой работает пользователь
     * @param commandsManager объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("Команда не принимает аргументы");
        else {
            System.exit(0);
        }
    }
}
