package commands;

import java.io.File;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import com.google.gson.JsonArray;

import collection.MyTreeSet;
import exceptions.ArgumentException;

public class ExecuteScript extends Command{
    String fileName;

    public ExecuteScript(JsonArray parameters, JsonArray arguments) {
        this.fileName = parameters.get(0).getAsString();
    }


    public ExecuteScript(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException{
        if (parameters.length != 1) throw new ArgumentException("Данная команда принимает на вход только один аргумент");
        this.fileName = parameters[0];
    }

    @Override
    public JsonArray getParameters() {
        JsonArray array = new JsonArray();
        array.add(fileName);
        return array;
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        try {
            File file = new File(fileName);
            Scanner scanner = new Scanner(file);
            List<String> output = new ArrayList<String>();
            while (scanner.hasNextLine()) {
                String line = "";
                do line = scanner.nextLine(); while (line.isEmpty());
                output.add(line);
                Command command = Command.fromConsole(line.split(" "), System.out, scanner);
                if (command instanceof ExecuteScript)
                    output.add("Выполнение скрипта пропущено из-за рекурсии");
                else
                    output.add(command.execute(treeSet));
            }
            output.add("Скрипт успешно выполнен");
            return output.stream().collect(Collectors.joining("\n"));
        } catch (Exception e) {
            return "Ошибка: " + e.getMessage();
        }
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("execute_script", "исполняет команды из файла");
    }
}
