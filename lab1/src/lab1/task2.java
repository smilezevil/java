package lab1;

public class task2 {
    public static void main(String[] args) {
        new task2().run();
    }
    public void run() {
        System.out.println("\nЗавдання 2:");
        int number = 16;

        if (number % 2 == 0) {
            System.out.println("Число " + number + " є парним");
        } else {
            System.out.println("Число " + number + " є непарним");
        }
    }
}