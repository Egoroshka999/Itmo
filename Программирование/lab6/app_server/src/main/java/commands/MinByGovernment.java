package commands;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.Scanner;

import collection.City;
import collection.MyTreeSet;
import exceptions.ArgumentException;

public class MinByGovernment extends Command{
    public MinByGovernment() {}

    public MinByGovernment(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        if (treeSet.getTreeSet().size() == 0)
            return "Список пуст";
        City minCity = treeSet.getTreeSet().stream().min(Comparator.comparingInt(City::getGovernmentID)).get();
        return "Минимальный элемент по поля government: " + minCity;
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("min_by_government", "выводит любой объект из коллекции, значение поля government которого является минимальным");
    }
}
