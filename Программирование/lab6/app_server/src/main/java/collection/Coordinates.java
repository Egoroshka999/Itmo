package collection;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;

/**
 * Класс с координатами City
 * 
 * @author Деревягин Егор, P3115
 */
public class Coordinates{
    private double x;
    private Float y; //Поле не может быть null

    /**
     * Конструктор класса
     * 
     * @param x координата x
     * @param y координата y
     */
    public Coordinates(double x, Float y) {
        setX(x);
        setY(y);
    }

    /**
     * @param x параметр сеттера координаты x
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * @return координату x
     */
    public double getX() {
        return x;
    }

    /**
     * @param y параметр сеттера координаты y
     */
    public void setY(Float y) {
        this.y = y;
    }

    /**
     * @return координату y
     */
    public Float getY() {
        return y;
    }


    /**
     * @return строковое представление координат
     */
    @Override
    public String toString() {
        return "координаты:" +
                "x=" + x +
                ", y=" + y;
    }

    public String serialize() {
        JsonArray array = new JsonArray();
        array.add(x);
        array.add(y);
        return array.toString();
    }

    public static Coordinates deserialize(String str) {
        JsonArray array = JsonParser.parseString(str).getAsJsonArray();
        double x = array.get(0).getAsDouble();
        Float y = array.get(1).getAsFloat();
        return new Coordinates(x, y);
    }
}