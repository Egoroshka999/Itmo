package lab5.Commands;

import java.util.TreeSet;

import lab5.InputHandler;
import lab5.Collections.City;

/**
 * Класс команды add
 * 
 * @author Деревягин Егор, P3115
 */
public class Add extends AbstractCommand {
    public Add() {
        name = "add";
        help = "выводит в стандартный поток вывода информацию о коллекции";
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
        try {
            if (args.length > 0) {
                System.out.println("На данном этапе команда не принимает аргументы");
                if (commandsManager.isScript()) {
                    commandsManager.commandRewider();
                }
            } else {
                if (treeSet.add(InputHandler.ArgumentsReader(commandsManager)) && !commandsManager.isNotPrintAdd()) {
                    System.out.println("Элемент добавлен в коллекцию");
                }
            }
        } catch (NullPointerException ignored) {
        }
    }
}
