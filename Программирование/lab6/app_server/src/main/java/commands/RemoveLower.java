package commands;

import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.JsonArray;

import collection.MyTreeSet;
import exceptions.ArgumentException;

public class RemoveLower extends Command{
    Integer area;

    public RemoveLower(JsonArray parameters, JsonArray arguments) {
        this.area = parameters.get(0).getAsInt();
     }

    public RemoveLower(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException{
        if (parameters.length != 1) throw new ArgumentException("Данная команда принимает на вход только один аргумент");
        this.area = Integer.valueOf(parameters[0]);
    }

    @Override
    public JsonArray getParameters() {
        JsonArray array = new JsonArray();
        array.add(area);
        return array;
    }

    @Override
    public String execute(MyTreeSet treeSet) {
       treeSet.getTreeSet().removeIf(element -> element.getArea() < area);
       return "Элементы удалены";
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("remove_lower", "удаляет из коллекции все элементы, меньшие чем заданный");
    }
}
