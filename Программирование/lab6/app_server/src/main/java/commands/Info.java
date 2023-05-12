package commands;

import java.io.PrintStream;
import java.util.Scanner;

import collection.MyTreeSet;
import exceptions.ArgumentException;

public class Info extends Command{
    public Info() {}

    public Info(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        return "Тип коллекции: " + treeSet.getTreeSet().getClass().getName() + "\n"
            + "Время создания коллекции: " + treeSet.getCreationDate().toString() + "\n"
            + "Количество элементов в коллеции: " + treeSet.getTreeSet().size();
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("info", "выводит в стандартный поток вывода информацию о коллекции");
    }
}
