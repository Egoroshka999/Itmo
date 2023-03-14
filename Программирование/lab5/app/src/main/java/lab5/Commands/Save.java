package lab5.Commands;

import java.io.FileOutputStream;
import java.util.TreeSet;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import lab5.InputHandler;
import lab5.Collections.City;

/**
 * ����� ������� save
 *
 * @author ��������� ����, P3115
 */
public class Save extends AbstractCommand{
    public Save() {
        name = "save";
        help = "��������� ��������� � ����";
    }    

    /**
     * ��������� ��������� � ����
     *
     * @param args            �� ��������� ����������
     * @param treeSet         ���������, ������� ����� ����������
     * @param commandsManager ������ ������ CommandsManager
     */
    @Override
    public void execute(String[] args, TreeSet<City> treeSet, CommandsManager commandsManager) {
        if (args.length > 0) System.out.println("������� �� ��������� ���������");
        else {
            try (FileOutputStream fos = new FileOutputStream(InputHandler.arguments[0])) {
                JsonArray array = new JsonArray();
                for (City city: treeSet){
                    JsonObject object = new JsonObject();
                    object.addProperty("name", city.getName());
                    object.addProperty("x", city.getCoordinates().getX());
                    object.addProperty("y", city.getCoordinates().getY());
                    object.addProperty("creation date", city.getCreationDate().toString());
                    object.addProperty("area", city.getArea());
                    object.addProperty("population", city.getPopulation());
                    object.addProperty("meters above sea level", city.getMetersAboveSeaLevel());
                    object.addProperty("climate", city.getClimate().name());
                    object.addProperty("government", city.getGovernment().name());
                    object.addProperty("standard of living", city.getStandardOfLiving().name());
                    object.addProperty("governor", city.getGovernor().getName());
                    array.add(object);
                }                
                String json = array.toString();
                fos.write(json.getBytes());
                System.out.println("��������� ������� ���������");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
 }
