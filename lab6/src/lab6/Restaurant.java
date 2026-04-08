package lab6;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Restaurant {
    private final BlockingQueue<Order> orders = new LinkedBlockingQueue<>();

    public void placeOrder(Order order) throws InterruptedException {
        System.out.println("Client " + order.clientId + " made an order");
        orders.put(order);
    }

    public Order takeOrder() throws InterruptedException {
        return orders.take();
    }
}