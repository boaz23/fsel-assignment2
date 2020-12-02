package hw2.data;

public class Order {

    private int orderId;
    private OrderInfo orderInfo;

    public Order(int orderId, OrderInfo orderInfo) {
        this.orderId = orderId;
        this.orderInfo = orderInfo;
    }

    public int getOrderId() {
        return orderId;
    }

    public OrderInfo getOrderInfo() {
        return orderInfo;
    }
}
