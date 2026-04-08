package lab6;
public class Main {
    public static void main(String[] args) {
        Restaurant restaurant = new Restaurant();

        for (int i = 1; i <= 2; i++) {
            new Waiter(i, restaurant).start();
        }

        for (int i = 1; i <= 5; i++) {
            new Client(i, restaurant).start();
        }
    }
}