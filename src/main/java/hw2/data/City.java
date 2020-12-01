package hw2.data;

import java.util.HashMap;
import java.util.Objects;

public class City {
    private final String name;
    private HashMap<String, Hall> halls;

    public City(String name) {
        this.name = name;
        this.halls = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public void addHall(Hall hall){
        halls.put(hall.getHallName(), hall);
    }

    public Hall getHall(String hallName){
        return halls.getOrDefault(hallName, null);
    }

    public boolean hasHall(String hallName){
        return halls.containsKey(hallName);
    }
}
