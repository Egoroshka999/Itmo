package lab5.Commands;

import lab5.Collections.*;
import lab5.Commands.CommandsManager;

import java.util.TreeSet;

/**
 * Класс команды update_id
 *
 * @author Деревягин Егор, P3115
 */
public class UpdateId extends AbstractCommand{
    public UpdateId() {
        name = "update_id";
        help = "обновляет значение элемента коллекции, id которого равен заданному";


    }

    /**
     * Обновляет элемент коллекции по id
     *
     * @param args            id
     * @param treeSet         коллекция, элемент которой нужно обновить
     * @param commandsManager объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        try {
            if (args.length != 1) {
                System.out.println("Команда принимает лишь один аргумент");
                if (commandsManager.isScript()) {
                    commandsManager.commandRewider();
                }
            } else {
                long oldIdSetter = City.idSetter;
                commandsManager.setNotPrintAdd(true);
                City.idSetter = Integer.parseInt(args[0]);
                String[] nargs = new String[]{"remove_by_id", args[0]};
                CommandsManager.ExecuteCommand(nargs, treeSet);
                if (commandsManager.isExist()) {
                    nargs = new String[]{"add"};
                    CommandsManager.ExecuteCommand(nargs, treeSet);
                    System.out.println("Элемент с id = " + (City.idSetter - 1) + " обновлён");
                }
                City.idSetter = oldIdSetter;
            }
        } catch (NullPointerException ignored) {
        } catch (Exception e) {
            System.out.println("Неверный тип аргумента");
            if (commandsManager.isScript()) {
                commandsManager.commandRewider();
            }
        }
        commandsManager.setNotPrintAdd(false);
    }
    
}
