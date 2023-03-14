package lab5.Collections;

import java.io.Serializable;
import java.time.ZonedDateTime;

/**
 * ����� City, ��������� �������� ����������� ���������
 * 
 * @author ��������� ����, P3115
 */

public class City implements Serializable {
    public static long idSetter = 1;
    private long id; //�������� ���� ������ ���� ������ 0, �������� ����� ���� ������ ���� ����������, �������� ����� ���� ������ �������������� �������������
    private String name; //���� �� ����� ���� null, ������ �� ����� ���� ������
    private Coordinates coordinates; //���� �� ����� ���� null
    private java.time.ZonedDateTime creationDate; //���� �� ����� ���� null, �������� ����� ���� ������ �������������� �������������
    private Integer area; //�������� ���� ������ ���� ������ 0
    private Integer population; //�������� ���� ������ ���� ������ 0, ���� �� ����� ���� null
    private Double metersAboveSeaLevel;
    private Climate climate; //���� ����� ���� null
    private Government government; //���� �� ����� ���� null
    private StandardOfLiving standardOfLiving; //���� ����� ���� null
    private Human governor; //���� �� ����� ���� null

    /**
     * ����������� ������
     * 
     * @param name                  ��������
     * @param coordinates           ����������
     * @param area                  �������
     * @param population            ���-�� ���������
     * @param metersAboveSeaLevel   ������ ��� ������� ����
     * @param climate               ��� �������
     * @param government            ����� ���������
     * @param standardOfLiving      �������� �����
     * @param governor              ���
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
     * @param name ��� City
     * @throws NullPointerException ���� ��� �� ������� ��� �����
     */
    public void setName(String name) throws NullPointerException {
        if (name != null && !name.isEmpty())
            this.name = name;
        else throw new NullPointerException();
    }

    /**
     * @return ��� City
     */
    public String getName(){
        return name;
    }

    /**
     * @param coordinates ���������� City
     * @throws NullPointerException ���� ���������� �� ������� ��� �����
     */
    public void setCoordinates(Coordinates coordinates) throws NullPointerException {
        if (coordinates != null)
            this.coordinates = coordinates;
        else throw new NullPointerException();
    }

    /**
     * @return ���������� City
     */
    public Coordinates getCoordinates(){
        return coordinates;
    }

    /**
     * 
     * @param creationDate ���� ��������
     * @throws NullPointerException ���� ���� �� �������
     */
    public void setCreationDate(ZonedDateTime creationDate) throws NullPointerException{ //�������� �Ѩ �� ��� ��� __________________________________________
        if (creationDate == null)
            throw new NullPointerException();
        this.creationDate = creationDate;   
    }

    /**
     * 
     * @return ���� ��������
     */
    public ZonedDateTime getCreationDate() {
        return creationDate;
    }

    /**
     * @param area ������� City
     * @throws NullPointerException ���� ������� �� ������� ��� �����
     */
    public void setArea(Integer area) throws NullPointerException {
        if (area > 0)
            this.area = area;
        else throw new NullPointerException();
    }

    /**
     * @return ������� City
     */
    public Integer getArea() {
        return area;
    }

    /**
     * @param population ���-�� ��������� City
     * @throws NullPointerException ���� ���-�� ��������� �� ������� ��� �����
     */
    public void setPopulation(Integer population) throws NullPointerException {
        if (population != null && population > 0)
            this.population = population;
        else throw new NullPointerException();
    }

    /**
     * @return ���-�� ��������� City
     */
    public Integer getPopulation(){
        return population;
    }

    /**
     * @param metersAboveSeaLevel ������ ��� ������� ���� City
     * @throws NullPointerException ���� ������ �� ������� ��� �����
     */
    public void setMetersAboveSeaLevel(Double metersAboveSeaLevel) throws NullPointerException {
        if (metersAboveSeaLevel != null)
            this.metersAboveSeaLevel = metersAboveSeaLevel;
        else throw new NullPointerException();
    }

    /**
     * @return ������ �� ������� ���� City
     */
    public Double getMetersAboveSeaLevel(){
        return metersAboveSeaLevel;
    }

    /**
     * @param climate ������ City
     */
    public void setClimate(Climate climate){
        this.climate = climate;
    }

    /**
     * @return ������ City
     */
    public Climate getClimate(){
        return climate;
    }

    /**
     * @param government ����� ��������� City
     * @throws NullPointerException ���� ����� ��������� �� ���� ������� ��� �����
     */
    public void setGovernment(Government government) throws NullPointerException {
        if (government != null)
            this.government = government;
        else throw new NullPointerException();
    }

    /**
     * @return ����� ��������� City
     */
    public Government getGovernment(){
        return government;
    }

    /**
     * @return ������ ���� government ������������ ������ Government
     */
    public int getGovernmentID(){
        return government.ordinal();
    }

    /**
     * @param standardOfLiving �������� ����� City
     */
    public void setStandardOfLiving(StandardOfLiving standardOfLiving){
        this.standardOfLiving = standardOfLiving;
    }

    /**
     * @return �������� ����� City
     */
    public StandardOfLiving getStandardOfLiving(){
        return standardOfLiving;
    }
    
    /**
     * @param governor ��� City
     * @throws NullPointerException ���� ��� �� ��� ������ ��� �����
     */
    public void setGovernor(Human governor) throws NullPointerException {
        if (governor != null)
            this.governor = governor;
        else throw new NullPointerException();
    }

    /**
     * @return ��� City
     */
    public Human getGovernor(){
        return governor;
    }

    /**
     *  @return ��������� ������������ �������� ���������
     */
    @Override
    public String toString() {
        return "\n" + "����� " +
                "id: " + id + "\n" +
                "�������� ������: " + name + "\n" +
                coordinates + "\n" +
                "���� ��������: " + creationDate + "\n" +
                "�������: " + area + "\n" +
                "���������� ���������: " + population + "\n" +
                "������ ��� ������� ����: " + metersAboveSeaLevel + "\n" +
                "������: " + climate + "\n" +
                "��� ���������: " + government + "\n" +
                "�������� �����: " + standardOfLiving + "\n" +
                "���: " + governor + "\n";
    }
}
