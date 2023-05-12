package commands;

import java.io.PrintStream;
import java.util.Optional;
import java.util.Scanner;

import com.google.gson.JsonArray;

import collection.City;
import collection.MyTreeSet;
import exceptions.ArgumentException;

public class RemoveByID extends Command{
    long id;

    public RemoveByID(JsonArray parameters, JsonArray arguments) {
        this.id = parameters.get(0).getAsLong();
    }


    public RemoveByID(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException{
        if (parameters.length != 1) throw new ArgumentException("Данная команда принимает на вход только один аргумент");
        this.id = Long.valueOf(parameters[0]);
    }

    @Override
    public JsonArray getParameters() {
        JsonArray array = new JsonArray();
        array.add(id);
        return array;
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        Optional<City> city = treeSet.getTreeSet().stream()
            .filter(element -> element.getId() == id)
            .findFirst();
        if(!city.isPresent())
            return "Элемент не найден";
        treeSet.getTreeSet().remove(city.get());
        return "Элемент удален";
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("remove_by_id", "удаляет элемент из коллекции по его id");
    }
}
