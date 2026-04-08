package lab6;
import java.util.concurrent.CountDownLatch;

public class Order {
    public final int clientId;
    public final CountDownLatch ready = new CountDownLatch(1);
    public final CountDownLatch paid = new CountDownLatch(1);

    public Order(int clientId) {
        this.clientId = clientId;
    }
}