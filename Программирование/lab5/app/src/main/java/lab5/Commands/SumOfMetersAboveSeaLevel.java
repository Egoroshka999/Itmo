package lab5.Commands;

import java.util.TreeSet;

import lab5.Collections.City;

/**
 * Класс команды sum_of_meters_above_sea_level
 *
 * @author Деревягин Егор, P3115
 */
public class SumOfMetersAboveSeaLevel extends AbstractCommand{
    public SumOfMetersAboveSeaLevel(){
        name = "sum_of_meters_above_sea_level";
        help = "выводит сумму значений поля metersAboveSeaLevel для всех элементов коллекции";
    } 
    
    /**
     * Выводит сумму значений поля metersAboveSeaLevel для всех элементов коллекции
     *
     * @param args            не принимает аргументов
     * @param treeSet         коллекция, элемент которой нужно обновить
     * @param commandsManager объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) {
            System.out.println("На данном этапе команда не принимает аргументы");
            commandsManager.commandRewider();
        } else {
            System.out.println(treeSet.stream().mapToDouble((t) -> t.getMetersAboveSeaLevel()).sum());
        }
        
    }
    
}
