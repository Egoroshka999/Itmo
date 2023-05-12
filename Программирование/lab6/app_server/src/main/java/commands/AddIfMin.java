package commands;

import java.io.PrintStream;
import java.util.Random;
import java.util.Scanner;
import com.google.gson.JsonArray;
import collection.City;
import collection.Climate;
import collection.Coordinates;
import collection.Government;
import collection.Human;
import collection.MyTreeSet;
import collection.StandardOfLiving;
import commands.arguments.ArgumentReader;
import exceptions.ArgumentException;

public class AddIfMin extends Command{
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Integer area; //Значение поля должно быть больше 0
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private Double metersAboveSeaLevel;
    private Climate climate; //Поле может быть null
    private Government government; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле может быть null
    private Human governor; //Поле не может быть null

    public AddIfMin(JsonArray parameters, JsonArray arguments) {
        this.id = arguments.get(0).getAsLong();
        this.name = arguments.get(1).getAsString();
        this.coordinates = Coordinates.deserialize(arguments.get(2).getAsString());
        this.area = arguments.get(3).getAsInt();
        this.population = arguments.get(4).getAsInt();
        this.metersAboveSeaLevel = arguments.get(5).getAsDouble();
        this.climate = Climate.valueOf(arguments.get(6).getAsString());
        this.government = Government.valueOf(arguments.get(7).getAsString());
        this.standardOfLiving = StandardOfLiving.valueOf(arguments.get(8).getAsString());
        this.governor = Human.deserialize(arguments.get(9).getAsString());
    }


    public AddIfMin(String[] parameters, PrintStream stdout, Scanner scanner) throws ArgumentException {
        if (parameters.length != 0) throw new ArgumentException("Данная команда не принимает аргументы");
        this.id = 0;
        this.name = ArgumentReader.readString(stdout, scanner, "Введите имя");
        double x = ArgumentReader.readDouble(stdout, scanner, "Введите координату x (не целое)");
        Float y = ArgumentReader.readFloat(stdout, scanner, "Введите координату y (не целое)");
        this.coordinates = new Coordinates(x, y);
        this.area = -1; do area = ArgumentReader.readInt(stdout, scanner, "Введите площадь города"); while (area <= 0);
        this.population = -1; do population = ArgumentReader.readInt(stdout, scanner, "Введите количество населения города"); while (population <= 0);
        this.metersAboveSeaLevel = ArgumentReader.readDouble(stdout, scanner, "Введите сколько метров над уровнем моря");
        this.climate = ArgumentReader.readEnum(stdout, scanner, "Выберите тип климата: (HUMIDSUBTROPICAL, HUMIDCONTINENTAL, MEDITERRANIAN, STEPPE, SUBARCTIC)", Climate.class);
        this.government = ArgumentReader.readEnum(stdout, scanner, "Выберите тип управления городом: (ARISTOCRACY, KRITARCHY, PATRIARCHY, THEOCRACY, TOTALITARIANISM)", Government.class);
        this.standardOfLiving = ArgumentReader.readEnum(stdout, scanner, "Выберите стандарт жизни населения: (ULTRA_HIGH, HIGH, LOW)", StandardOfLiving.class);
        this.governor = new Human(ArgumentReader.readString(stdout, scanner, "Введите имя мера города"));
    }

    @Override
    public JsonArray getArguments() {
        JsonArray array = new JsonArray();
        array.add(id);
        array.add(name);
        array.add(coordinates.serialize());
        array.add(area);
        array.add(population);
        array.add(metersAboveSeaLevel);
        array.add(climate.toString());
        array.add(government.toString());
        array.add(standardOfLiving.toString());
        array.add(governor.serialize());
        return array;
    }

    @Override
    public String execute(MyTreeSet treeSet) {
        final long[] id = new long[1];
        do {
            id[0] = (new Random()).nextInt(99999999 - 10000000) + 10000000;
        } while(treeSet.getTreeSet().stream().anyMatch(item -> item.getId() == id[0]));
        this.id = id[0];
        
        City city = new City(this.id, name, coordinates, area, population, metersAboveSeaLevel, climate, government, standardOfLiving, governor);
        if(treeSet.getTreeSet().stream().anyMatch((t) -> t.getArea() < city.getArea()))
            return "Элемент не добавлен, так как не является минимальным";
        treeSet.getTreeSet().add(city);
        return "Элемент добавлен в коллекцию";
    }

    public static Help.HelpEntry getHelpEntry() {
        return new Help.HelpEntry("add_if_min", "добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции");
    }
}
