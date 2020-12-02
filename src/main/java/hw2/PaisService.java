package hw2;

import hw2.bridge.Bridge;
import hw2.data.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PaisService implements Bridge {

    private CityRepository cityRepository;
    private AdminRepository adminRepository;
    private ShowRepository showRepository;
    private OrderRepository orderRepository;

    public PaisService() {
        cityRepository = new CityRepository();
        adminRepository = new AdminRepository();
        showRepository = new ShowRepository();
        orderRepository = new OrderRepository();
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
        if(!showRepository.hasShow(showID)){
            return;
        }
        if(from < 1 | to < 1 | from > to){
            return;
        }

        Show show = showRepository.getShow(showID);
        Hall showHall = show.getHall();
        if(to > showHall.getSeatsAmount()){
            return;
        }

        show.reserveMemberChairs(from, to);
    }

    @Override
    public int newOrder(OrderInfo order) {
        if(order == null){
            return 0;
        }
        if(!showRepository.hasShow(order.showId)){
            return 0;
        }

        try{
            checkStringNullOrEmpty(order.name, "Name of order request");
            checkStringNullOrEmpty(order.phone, "Phone number of order request");
        } catch (IllegalArgumentException e){
            return 0;
        }
        if(order.chairsIds == null || order.chairsIds.length == 0){
            return 0;
        }


        Show show = showRepository.getShow(order.showId);

        if(show.getLastOrderDate() < (new Date()).getTime()){
            return 0;
        }

        int chairAmount = show.getHall().getSeatsAmount();
        for(int chairsId : order.chairsIds){
            if(chairsId < 1 | chairsId > chairAmount){
                return 0;
            }
            if(order.memberId == null & show.isChairReserved(chairsId)){
                return 0;
            }
        }

        Order orderWrapper = orderRepository.addOrder(order);
        show.addUserToInform(orderWrapper);

        return orderWrapper.getOrderId();
    }

    @Override
    public List<OrderInfo> getWaitings(int showId) {
        if (!showRepository.hasShow(showId)){
            return null;
        }
        Show show = showRepository.getShow(showId);
        List<OrderInfo> orderInfos = new ArrayList<>();

        for(Order order : show.getUsersToInform()){
            orderInfos.add(order.getOrderInfo());
        }

        return orderInfos;

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
