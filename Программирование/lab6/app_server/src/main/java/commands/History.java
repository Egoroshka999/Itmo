package commands;

import java.io.PrintStream;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.stream.Collectors;

import collection.MyTreeSet;
import exceptions.ArgumentException;

public class History extends Command{
    private static LinkedList<String> items = new LinkedList<String>();

    public static void push(String command) {
        items.addLast(command);
        if(items.size() > 8) items.removeFirst();
    }

    public History() {}

    public History(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        String result = items.stream().collect(Collectors.joining("\n"));
        return "Последние 8 команд:\n" + result;
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("history", "выводит последние 8 команд");
    }
}
