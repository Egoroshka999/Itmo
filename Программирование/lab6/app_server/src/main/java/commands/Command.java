package commands;

import java.io.PrintStream;
import java.util.Scanner;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import collection.MyTreeSet;
import exceptions.ArgumentException;
import exceptions.CommandNotFoundException;

public abstract class Command {
    public static Command fromJson(String data) throws CommandNotFoundException {
        JsonObject object = JsonParser.parseString(data).getAsJsonObject();
        
        String type = object.get("type").getAsString();
        JsonArray parameters = object.get("parameters").getAsJsonArray();
        JsonArray arguments = object.get("arguments").getAsJsonArray();

        switch(type) {
            case "Help":
                return new Help();
            case "Add":
                return new Add(parameters, arguments);
            case "Show":
                return new Show();
            case "AddIfMin":
                return new AddIfMin(parameters, arguments);
            case "Clear":
                return new Clear();
            case "History":
                return new History();
            case "Info":
                return new Info();
            case "MinByGovernment":
                return new MinByGovernment();
            case "PrintFieldDescendingGovernment":
                return new PrintFieldDescendingGovernment();
            case "RemoveByID":
                return new RemoveByID(parameters, arguments);
            case "RemoveLower":
                return new RemoveLower(parameters, arguments);
            case "SumOfMetersAboveSeaLevel":
                return new SumOfMetersAboveSeaLevel();
            case "UpdateID":
                return new UpdateID(parameters, arguments);
            case "ExecuteScript":
                return new ExecuteScript(parameters, arguments);
            default:
                throw new CommandNotFoundException();
        }
    }

    public static Command fromConsole(String[] cmd, PrintStream stdout, Scanner scanner) throws CommandNotFoundException, InterruptedException, ArgumentException{
        String[] parameters = new String[cmd.length - 1];
        System.arraycopy(cmd, 1, parameters, 0, parameters.length);
        switch(cmd[0]) {
            case "help":
                return new Help(parameters, stdout, scanner);
            case "add":
                return new Add(parameters, stdout, scanner);
            case "show":
                return new Show(parameters, stdout, scanner);
            case "add_if_min":
                return new AddIfMin(parameters, stdout, scanner);
            case "clear":
                return new Clear(parameters, stdout, scanner);
            case "history":
                return new History(parameters, stdout, scanner);
            case "exit":
                throw new InterruptedException();
            case "info":
                return new Info(parameters, stdout, scanner);
            case "min_by_government":
                return new MinByGovernment(parameters, stdout, scanner);
            case "government_list":
                return new PrintFieldDescendingGovernment(parameters, stdout, scanner);
            case "remove_by_id":
                return new RemoveByID(parameters, stdout, scanner);
            case "remove_lower":
                return new RemoveLower(parameters, stdout, scanner);
            case "sum_sea_level":
                return new SumOfMetersAboveSeaLevel(parameters, stdout, scanner);
            case "update_id":
                return new UpdateID(parameters, stdout, scanner);
            case "execute_script":
                return new ExecuteScript(parameters, stdout, scanner);
            default:
                throw new CommandNotFoundException();
        }
    }

    public String serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("type", getClass().getSimpleName());
        object.add("parameters", getParameters());
        object.add("arguments", getArguments());
        return object.toString();
    }

    public String execute(MyTreeSet treeSet) {
        return null;
    }

    public JsonArray getParameters() {
        return new JsonArray();
    }

    public JsonArray getArguments() {
        return new JsonArray();
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("Optimus", "Prime");
    }
}
