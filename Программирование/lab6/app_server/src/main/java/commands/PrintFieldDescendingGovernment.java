package commands;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import collection.City;
import collection.Government;
import collection.MyTreeSet;
import exceptions.ArgumentException;

public class PrintFieldDescendingGovernment extends Command{
    public PrintFieldDescendingGovernment() {}

    public PrintFieldDescendingGovernment(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        if (treeSet.getTreeSet().size() == 0)
            return "Список пуст";
        List<Government> sortedGovernmentIDs = treeSet.getTreeSet().stream()
            .map(City::getGovernmentID)
            .sorted(Comparator.reverseOrder())
            .map(govId -> Government.values()[govId])
            .collect(Collectors.toList());

        return sortedGovernmentIDs.toString();
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("print_field_descending_government", "выводит значения поля government всех элементов в порядке убывания");
    }
}
