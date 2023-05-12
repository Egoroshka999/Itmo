package commands;

import java.io.PrintStream;
import java.util.Scanner;
import collection.MyTreeSet;
import exceptions.ArgumentException;

public class Show extends Command {
    public Show() {}

    public Show(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        return treeSet.getTreeSet().toString();
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("show", "выводит в стандартный поток вывода все элементы коллекции в строковом представлении");
    }
}
