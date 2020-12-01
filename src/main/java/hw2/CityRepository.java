package hw2;

import hw2.data.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class CityRepository {
    HashMap<String, City> citiesName;

    public CityRepository() {
        citiesName = new HashMap<>();
    }

    public void addCity(City city){
        citiesName.put(city.getName(), city);
    }

    public City getCity(String cityName){
        return citiesName.getOrDefault(cityName, null);
    }

    public boolean hasCity(String cityName){
        return citiesName.containsKey(cityName);
    }

    public int size(){
        return citiesName.size();
    }
}
