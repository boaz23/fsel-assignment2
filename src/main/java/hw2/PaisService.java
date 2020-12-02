package hw2;

import hw2.bridge.Bridge;
import hw2.data.*;

import java.util.List;

public class PaisService implements Bridge {

    private CityRepository cityRepository;
    private AdminRepository adminRepository;
    private ShowRepository showRepository;

    public PaisService() {
        cityRepository = new CityRepository();
        adminRepository = new AdminRepository();
        showRepository = new ShowRepository();
    }

    @Override
    public void addCity(String city) {
        checkStringNullOrEmpty(city, "City");
        cityRepository.addCity(new City(city));
    }

    @Override
    public void addHall(String cityName, String hall, int sits) {
        checkCity(cityName);
        checkStringNullOrEmpty(hall, "Hall");
        if(sits <= 0){
            throw new IllegalArgumentException("Number of seats in the hall need to be positive number");
        }

        City city = cityRepository.getCity(cityName);
        city.addHall(new Hall(hall, sits));
    }

    @Override
    public void addAdmin(String cityName, String user, String pass) {
        checkCity(cityName);
        checkStringNullOrEmpty(user, "Username");
        checkStringNullOrEmpty(pass, "Password");

        City city = cityRepository.getCity(cityName);
        adminRepository.addAdmin(new Admin(user, pass, city));
    }

    @Override
    public int addNewShow(String user, String pass, ShowInfo showInfo) {

        try {
            checkStringNullOrEmpty(user, "Username");
            checkStringNullOrEmpty(pass, "Password");
            if(showInfo == null){
                return 0;
            }
            checkCity(showInfo.city);
            checkStringNullOrEmpty(showInfo.hall, "Hall");
            checkStringNullOrEmpty(showInfo.name, "Show name");
            checkStringNullOrEmpty(showInfo.description, "Show description");
        } catch (IllegalArgumentException e){
            return 0;
        }
        if(showInfo.showDate <= 0 | showInfo.ticketCost <= 0 | showInfo.lastOrderDate <= 0 ){
            return 0;
        }
        if(showInfo.showDate <= showInfo.lastOrderDate){
            return 0;
        }
        if((showInfo.showTime == null) == showInfo.hastime){
            return 0;
        }

        City city = cityRepository.getCity(showInfo.city);
        if(!city.hasHall(showInfo.hall)){
            return 0;
        }

        if(!adminRepository.hasAdmin(user)){
            return 0;
        }
        Admin admin = adminRepository.getAdmin(user);
        if(!admin.getPassword().equals(pass)){
            return 0;
        }
        if(!admin.getCity().getName().equals(city.getName())){
            return 0;
        }

        Show newShow = new Show(city, city.getHall(showInfo.hall), showInfo.name, showInfo.description,
                                showInfo.lastOrderDate, showInfo.showTime, showInfo.showDate, showInfo.ticketCost);
        return showRepository.addShow(newShow);
    }

    @Override
    public void reserveMemberChairs(int showID, int from, int to) {

    }

    @Override
    public int newOrder(OrderInfo order) {
        return 0;
    }

    @Override
    public List<OrderInfo> getWaitings(int id) {
        return null;
    }

    private void checkStringNullOrEmpty(String str, String parameterName){
        if(str == null || str.length() == 0){
            throw new IllegalArgumentException(parameterName + " cant be null or empty");
        }
    }

    private void checkCity(String city){
        checkStringNullOrEmpty(city, "City");
        if(!cityRepository.hasCity(city)){
            throw new IllegalArgumentException("The city " + city +" doesnt exists");
        }

    }
}
