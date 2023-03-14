package lab5.Collections;


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
}
