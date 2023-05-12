package collection;

import java.time.ZonedDateTime;
import java.util.TreeSet;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

public class MyTreeSet {
    TreeSet<City> treeSet;
    ZonedDateTime creationDate;

    public MyTreeSet() {
        this.creationDate = ZonedDateTime.now();
        this.treeSet = new TreeSet<City>((City o1, City o2) -> (Long.compare(o1.getId(), o2.getId())));
    }

    public MyTreeSet(String serialized) {
        this.treeSet = new TreeSet<City>((City o1, City o2) -> (Long.compare(o1.getId(), o2.getId())));
        
        JsonObject object = JsonParser.parseString(serialized).getAsJsonObject();
        this.creationDate = ZonedDateTime.parse(object.get("creation date").getAsString());

        JsonArray array = object.get("items").getAsJsonArray();
        for (int i = 0; i < array.size(); i++) {
            JsonObject cityObject = array.get(i).getAsJsonObject();
            City city = new City(cityObject.get("id").getAsLong(), cityObject.get("name").getAsString(),
                new Coordinates(cityObject.get("x").getAsDouble(), cityObject.get("y").getAsFloat()),
                cityObject.get("area").getAsInt(), cityObject.get("population").getAsInt(),
                cityObject.get("meters above sea level").getAsDouble(), Climate.valueOf(cityObject.get("climate").getAsString()),
                Government.valueOf(cityObject.get("government").getAsString()),
                StandardOfLiving.valueOf(cityObject.get("standard of living").getAsString()), new Human(cityObject.get("governor").getAsString()));
            city.setCreationDate(ZonedDateTime.parse(cityObject.get("creation date").getAsString()));
            treeSet.add(city);
        }
    }
    
    public String serialize() {
        JsonObject object = new JsonObject();
        object.addProperty("creation date", creationDate.toString());

        JsonArray array = new JsonArray();
        for (City city : treeSet){
            JsonObject cityObject = new JsonObject();
            cityObject.addProperty("id", city.getId());
            cityObject.addProperty("name", city.getName());
            cityObject.addProperty("x", city.getCoordinates().getX());
            cityObject.addProperty("y", city.getCoordinates().getY());
            cityObject.addProperty("creation date", city.getCreationDate().toString());
            cityObject.addProperty("area", city.getArea());
            cityObject.addProperty("population", city.getPopulation());
            cityObject.addProperty("meters above sea level", city.getMetersAboveSeaLevel());
            cityObject.addProperty("climate", city.getClimate().name());
            cityObject.addProperty("government", city.getGovernment().name());
            cityObject.addProperty("standard of living", city.getStandardOfLiving().name());
            cityObject.addProperty("governor", city.getGovernor().getName());
            array.add(cityObject);
        }
        object.add("items", array);

        return object.toString();
    }

    public TreeSet<City> getTreeSet() {
        return this.treeSet;
    }

    public ZonedDateTime getCreationDate() {
        return creationDate;
    }
}
