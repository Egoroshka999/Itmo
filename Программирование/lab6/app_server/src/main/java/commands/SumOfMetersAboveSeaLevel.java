package commands;

import java.io.PrintStream;
import java.util.Scanner;

import collection.MyTreeSet;
import exceptions.ArgumentException;

public class SumOfMetersAboveSeaLevel extends Command{
    public SumOfMetersAboveSeaLevel() {}

    public SumOfMetersAboveSeaLevel(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {

        return Double.toString(treeSet.getTreeSet().stream().mapToDouble((t) -> t.getMetersAboveSeaLevel()).sum());
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("sum_of_meters_above_sea_level", "выводит сумму значений поля metersAboveSeaLevel для всех элементов коллекции");
    }
}
