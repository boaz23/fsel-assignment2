package hw2;

import hw2.data.Order;
import hw2.data.OrderInfo;
import hw2.data.Show;

import java.util.HashMap;

public class OrderRepository {
    private int nextOrderId;
    private HashMap<Integer, Order> orders;

    public OrderRepository() {
        this.nextOrderId = 1;
        this.orders = new HashMap<>();
    }

    public Order addOrder(OrderInfo order){
        int orderId = nextOrderId++;
        Order newOrder = new Order(orderId, order);
        orders.put(orderId, newOrder);
        return newOrder;
    }

    public Order getShow(int orderId){
        return orders.getOrDefault(orderId, null);
    }

    public boolean hasShow(int orderId){
        return orders.containsKey(orderId);
    }

    public int size(){
        return orders.size();
    }
}
