package lab5;

import lab5.Collections.*;
import lab5.Commands.CommandsManager;

import java.util.*;

/**
 * Main class
 *
 * @author Деревягин Егор, P3115
 */
public class App {
    public static Date creationDate;

    /**
     * метод main
     *
     * @param args имя файла, для загрузки коллекции и сплитер для парсинга (опционально)
     */
    public static void main(String[] args) {
        TreeSet<City> treeSet = new TreeSet<>(idComparator);
        creationDate = new Date();
        Scanner scanner = new Scanner(System.in);
        try {   
            if (args.length == 0){
                args = new String[]{"C:\\Users\\Doctor_Strange\\Desktop\\file.json"};
            }         
            if (args.length > 1) {
                System.out.println("Неверное количество аргументов");
                System.exit(-1);
            }

            InputHandler.InputLoader(args, treeSet);
            System.out.println("Коллекция загружена");
            while (true) {
                CommandsManager.ExecuteCommand(scanner.nextLine().split(" "), treeSet);
            }
            
        } catch (Exception e) {
            System.out.println("Не удалось загрузить файл");
            e.printStackTrace();
        }
        scanner.close();
        
    }
    /**
     * Переопределение интерфейса Comparator для сравнения элементов коллекции по полю Area
     */
    public static Comparator<City> idComparator = (City o1, City o2) -> (Long.compare(o1.getId(), o2.getId()));
}
