package lab6;
public class Client extends Thread {
    private final int id;
    private final Restaurant restaurant;

    public Client(int id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        try {
            System.out.println("Client " + id + " arrived");

            Order order = new Order(id);
            restaurant.placeOrder(order);

            order.ready.await();
            System.out.println("Client " + id + " received the order");

            Thread.sleep((int)(Math.random() * 1000));

            System.out.println("Client " + id + " paid");
            order.paid.countDown();

            System.out.println("Client " + id + " left");

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}