package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * Класс команды min_by_government
 *
 * @author Деревягин Егор, P3115
 */
public class MinByGovernment extends AbstractCommand{
    public MinByGovernment(){
        name = "min_by_government";
        help = "выводит люой объект из коллекции, значение поля government которого является минимальным";
    }


    /**
     * Выводит элемент коллекции с минимальным government
     *
     * @param args              не принимает аргументов
     * @param treeSet           коллекция, с которой работает пользователь
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("Команда не принимает аргументы");
        else{
            try {
                if (treeSet.size() > 0) {
                    int min = Integer.MAX_VALUE;
                    City minCity = null;
                    for (City city : treeSet) {
                        if (city.getGovernmentID() < min) {
                            min = city.getGovernmentID();
                            minCity = city;
                        }
                    }
                    System.out.println("Минимальный элемент по поля government: " + minCity);
                } else System.out.println("Список пуст");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
