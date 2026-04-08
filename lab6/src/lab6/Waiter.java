package lab6;
public class Waiter extends Thread {
    private final int id;
    private final Restaurant restaurant;

    public Waiter(int id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Order order = restaurant.takeOrder();
                System.out.println("Waiter " + id + " is serving client " + order.clientId);

                Thread.sleep((int)(Math.random() * 2000));
                System.out.println("Order for client " + order.clientId + " is ready");

                order.ready.countDown();
                order.paid.await();

                System.out.println("Waiter " + id + " finished serving client " + order.clientId);
            }
        } catch (InterruptedException e) {
            System.out.println("Waiter " + id + " finished work");
        }
    }
}