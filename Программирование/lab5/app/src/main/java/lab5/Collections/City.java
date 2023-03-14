package lab5.Collections;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * Класс City, объектами которого заполняется коллекция
 * 
 * @author Деревягин Егор, P3115
 */

public class City implements Serializable {
    public static long idSetter = 1;
    private long id; //Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private java.time.ZonedDateTime creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private Integer area; //Значение поля должно быть больше 0
    private Integer population; //Значение поля должно быть больше 0, Поле не может быть null
    private Double metersAboveSeaLevel;
    private Climate climate; //Поле может быть null
    private Government government; //Поле не может быть null
    private StandardOfLiving standardOfLiving; //Поле может быть null
    private Human governor; //Поле не может быть null

    /**
     * Конструктор класса
     * 
     * @param name                  название
     * @param coordinates           координаты
     * @param area                  площадь
     * @param population            кол-во населения
     * @param metersAboveSeaLevel   высота над уровнем моря
     * @param climate               тип климата
     * @param government            форма правления
     * @param standardOfLiving      стандарт жизни
     * @param governor              мэр
     */

    public City(String name, Coordinates coordinates,Integer area, Integer population, Double metersAboveSeaLevel, Climate climate, Government government, StandardOfLiving standardOfLiving, Human governor){
        setCreationDate(ZonedDateTime.now());
        setId(idSetter++);
        setName(name);
        setCoordinates(coordinates);
        setArea(area);
        setPopulation(population);
        setMetersAboveSeaLevel(metersAboveSeaLevel);
        setClimate(climate);
        setGovernment(government);
        setStandardOfLiving(standardOfLiving);
        setGovernor(governor);
    }


    /**
     * @param id City
     */
    public void setId(long id){
        this.id = id;
    }

    /**
     * @return id City
     */
    public long getId(){
        return id;
    }

    /**
     * @param name имя City
     * @throws NullPointerException если имя не указано при вводе
     */
    public void setName(String name) throws NullPointerException {
        if (name != null && !name.isEmpty())
            this.name = name;
        else throw new NullPointerException();
    }

    /**
     * @return имя City
     */
    public String getName(){
        return name;
    }

    /**
     * @param coordinates координаты City
     * @throws NullPointerException если координаты не указаны при вводе
     */
    public void setCoordinates(Coordinates coordinates) throws NullPointerException {
        if (coordinates != null)
            this.coordinates = coordinates;
        else throw new NullPointerException();
    }

    /**
     * @return координаты City
     */
    public Coordinates getCoordinates(){
        return coordinates;
    }

    /**
     * 
     * @param creationDate дата создания
     * @throws NullPointerException если дата не указана
     */
    public void setCreationDate(ZonedDateTime creationDate) throws NullPointerException{ //ПОМЕНЯТЬ ВСЁ НА КАК ТУТ __________________________________________
        if (creationDate == null)
            throw new NullPointerException();
        this.creationDate = creationDate;   
    }

    /**
     * 
     * @return дату создания
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param area площадь City
     * @throws NullPointerException если площадь не указана при вводе
     */
    public void setArea(Integer area) throws NullPointerException {
        if (area > 0)
            this.area = area;
        else throw new NullPointerException();
    }

    /**
     * @return площадь City
     */
    public Integer getArea() {
        return area;
    }

    /**
     * @param population кол-во населения City
     * @throws NullPointerException если кол-во населения не указано при вводе
     */
    public void setPopulation(Integer population) throws NullPointerException {
        if (population != null && population > 0)
            this.population = population;
        else throw new NullPointerException();
    }

    /**
     * @return кол-во населения City
     */
    public Integer getPopulation(){
        return population;
    }

    /**
     * @param metersAboveSeaLevel высота над уровнем моря City
     * @throws NullPointerException если высота не указана при вводе
     */
    public void setMetersAboveSeaLevel(Double metersAboveSeaLevel) throws NullPointerException {
        if (metersAboveSeaLevel != null)
            this.metersAboveSeaLevel = metersAboveSeaLevel;
        else throw new NullPointerException();
    }

    /**
     * @return высота на уровнем моря City
     */
    public Double getMetersAboveSeaLevel(){
        return metersAboveSeaLevel;
    }

    /**
     * @param climate климат City
     */
    public void setClimate(Climate climate){
        this.climate = climate;
    }

    /**
     * @return климат City
     */
    public Climate getClimate(){
        return climate;
    }

    /**
     * @param government форма правления City
     * @throws NullPointerException если форма правления не была указана при вводе
     */
    public void setGovernment(Government government) throws NullPointerException {
        if (government != null)
            this.government = government;
        else throw new NullPointerException();
    }

    /**
     * @return форма правления City
     */
    public Government getGovernment(){
        return government;
    }

    /**
     * @return индекс поля government относительно класса Government
     */
    public int getGovernmentID(){
        return government.ordinal();
    }

    /**
     * @param standardOfLiving стандарт жизни City
     */
    public void setStandardOfLiving(StandardOfLiving standardOfLiving){
        this.standardOfLiving = standardOfLiving;
    }

    /**
     * @return стандарт жизни City
     */
    public StandardOfLiving getStandardOfLiving(){
        return standardOfLiving;
    }
    
    /**
     * @param governor мэр City
     * @throws NullPointerException если мэр не был указан при вводе
     */
    public void setGovernor(Human governor) throws NullPointerException {
        if (governor != null)
            this.governor = governor;
        else throw new NullPointerException();
    }

    /**
     * @return мэр City
     */
    public Human getGovernor(){
        return governor;
    }

    /**
     *  @return строковое представлние элемента коллекции
     */
    @Override
    public String toString() {
        return "\n" + "Город " +
                "id: " + id + "\n" +
                "название города: " + name + "\n" +
                coordinates + "\n" +
                "дата создания: " + creationDate + "\n" +
                "площадь: " + area + "\n" +
                "количество населения: " + population + "\n" +
                "метров над уровнем моря: " + metersAboveSeaLevel + "\n" +
                "климат: " + climate + "\n" +
                "тип правления: " + government + "\n" +
                "стандарт жизни: " + standardOfLiving + "\n" +
                "мэр: " + governor + "\n";
    }
}
