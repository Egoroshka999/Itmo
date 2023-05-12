package collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Класс мэра City
 * 
 * @author Деревягин Егор, P3115
 */
public class Human {
    private String name; //Поле не может быть null, Строка не может быть пустой

    /**
     * Конструктор мэра
     * 
     * @param name имя
     */
    public Human(String name){
        setName(name);
    }

    /**
     * @param name имя мэра
     * @throws NullPointerException если имя не указано при вводе
     */
    public void setName(String name) throws NullPointerException {
        if (name != null && !name.isEmpty())
            this.name = name;
        else throw new NullPointerException();
    }

    /**
     * @return имя мэра
     */
    public String getName() {
        return name;
    }

    /**
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return name;
    }

    public String serialize() {
        JsonArray array = new JsonArray();
        array.add(name);
        return array.toString();
    }

    public static Human deserialize(String str) {
        JsonArray array = JsonParser.parseString(str).getAsJsonArray();
        String name = array.get(0).getAsString();
        return new Human(name);
    }
}
