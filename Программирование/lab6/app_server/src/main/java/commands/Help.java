package commands;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import collection.MyTreeSet;
import exceptions.ArgumentException;

public class Help extends Command {
    public static List<HelpEntry> entries = new ArrayList<HelpEntry>();
    public static void registerEntry(HelpEntry entry) {
        entries.add(entry);
    }

    public Help() {}

    public Help(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        String joinedHelpEntries = entries.stream().map(HelpEntry::toString).collect(Collectors.joining("\n"));

        return joinedHelpEntries;
    }

    public static class HelpEntry {
        String name;
        String desc;

        public HelpEntry(String name, String desc) {
            this.name = name;
            this.desc = desc;
        }
        @Override
        public String toString() {
            return this.name + " - " + this.desc; 
        }
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("help", "выводит справку по доступным командам");
    }
}
