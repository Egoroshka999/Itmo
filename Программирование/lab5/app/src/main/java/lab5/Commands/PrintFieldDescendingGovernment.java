package lab5.Commands;

import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

import lab5.Collections.City;
import lab5.Collections.Government;

/**
 * Класс команды print_field_descending_government
 *
 * @author Деревягин Егор, P3115
 */
public class PrintFieldDescendingGovernment extends AbstractCommand{
    public PrintFieldDescendingGovernment(){
        name = "print_field_descending_government";
        help = "выводит значения поля government всех элементов в порядке убывания";
    }

    /**
     * Выводит значения поля government всех элементов в порядке убывания
     *
     * @param args              не принимает аргументы
     * @param treeSet           коллекция, с которой работает пользователь
     * @param commandsManager   объект класса CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("Команда не принимает аргументы");
        else{
            try {
                if (treeSet.size() > 0) {
                    ArrayList<Integer> governmentID = new ArrayList<Integer>(treeSet.size());
                    for (City city : treeSet) {
                        governmentID.add(city.getGovernmentID());
                    }
                    Collections.sort(governmentID, Collections.reverseOrder());
                   System.out.println("Значения government всех элементов в порядке убывания: ");
                    for (int x: governmentID){
                        System.out.println(Government.values()[x].name());
                    }                    
                } else System.out.println("Список пуст");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
    }
    
}
