import hw2.CityRepository;
import hw2.data.City;

public class Main {

    public static void main(String[] args){
        City c1 = new City("a");
        City c2 = new City("a");

        CityRepository repository = new CityRepository();
        repository.addCity(c1);
        repository.addCity(c2);

        System.out.println(repository.size());
    }
}
