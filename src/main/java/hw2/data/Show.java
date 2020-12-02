package hw2.data;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Show {

    private int id;
    private City city;
    private Hall hall;
    private String name;
    private String description;
    private long lastOrderDate;
    private LocalTime showTime;
    private long showDate;
    private double ticketCost;
    private List<Order> usersToInform;
    private boolean[] reservedForMembers;

    public Show( City city, Hall hall, String name, String description, long lastOrderDate, LocalTime showTime, long showDate, double ticketCost) {
        this.city = city;
        this.hall = hall;
        this.name = name;
        this.description = description;
        this.lastOrderDate = lastOrderDate;
        this.showTime = showTime;
        this.showDate = showDate;
        this.ticketCost = ticketCost;
        usersToInform = new ArrayList<>();
        this.reservedForMembers = new boolean[hall.getSeatsAmount()];
    }

    public boolean hasTime(){
        return showTime != null;
    };

    public void setId(int id) {
        this.id = id;
    }

    public void reserveMemberChairs(int from, int to) {
        for (int i = from - 1; i < to; i++) {
            reservedForMembers[i] = true;
        }
    }

    public void addUserToInform(Order order) {
        for (Order orderToInform : usersToInform) {
            if (orderToInform.getOrderInfo().name.equals(order.getOrderInfo().name)) {
                return;
            }
        }

        usersToInform.add(order);
    }

    public boolean isChairReserved(int chairNumber){
        return reservedForMembers[chairNumber-1];
    }

    public int getId() {
        return id;
    }

    public City getCity() {
        return city;
    }

    public Hall getHall() {
        return hall;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public long getLastOrderDate() {
        return lastOrderDate;
    }

    public LocalTime getShowTime() {
        return showTime;
    }

    public long getShowDate() {
        return showDate;
    }

    public double getTicketCost() {
        return ticketCost;
    }

    public List<Order> getUsersToInform() {
        return usersToInform;
    }
}
