package commands;

import java.io.PrintStream;
import java.util.Scanner;
import collection.MyTreeSet;
import exceptions.ArgumentException;

public class Clear extends Command{
    public Clear() {}

    public Clear(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        treeSet.getTreeSet().clear();
        return "Коллекция очищена";
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("clear", "очищает коллекцию");
    }
}
