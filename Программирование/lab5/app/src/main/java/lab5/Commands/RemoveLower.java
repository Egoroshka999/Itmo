package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 *  ласс команды remove_lower
 *
 * @author ƒерев€гин ≈гор, P3115
 */
public class RemoveLower extends AbstractCommand{
    public RemoveLower() {
        name = "remove_lower";
        help = "удал€ет из коллекции все элементы, меньшие чем заданный";
    }

    /**
     * ”дал€ет из коллекции все элементы, меньшие чем заданный
     *
     * @param args              не принимает аргументы
     * @param treeSet           коллекци€, из которой удал€ютс€ элементы
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("Ќа данном этапе команда не принимает аргументы");
            commandsManager.commandRewider();
        } else {
            try {
                if (treeSet.size() > 0) {
                    commandsManager.setNotPrintAdd(true);
                    String[] nargs = new String[]{"add"};
                    CommandsManager.ExecuteCommand(nargs, treeSet);
                    City added = null;
                    for (City each : treeSet) {
                        if (each.getId() == City.idSetter - 1)
                            added = each;
                    }
                    int area = added.getArea();
                    treeSet.removeIf(city -> city.getArea() < area);
                    treeSet.remove(added);
                    City.idSetter--;
                    System.out.println("Ёлементы меньшие, чем заданный удалены из коллекции");
                } else System.out.println("—писок пуст");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        commandsManager.setNotPrintAdd(false);
    }
    

    
}
