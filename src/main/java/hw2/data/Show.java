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
    private List<OrderInfo> usersToInform;

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
    }

    public boolean hasTime(){
        return showTime != null;
    };

    public void setId(int id) {
        this.id = id;
    }
}
