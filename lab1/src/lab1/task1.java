package lab1;

public class task1 {
    public static void main(String[] args) {
        new task1().run();
    }
    public void run() {
        System.out.println("\nЗавдання 1:");
        char character = '0';
        int asciiValue = (int) character;
        System.out.println("ASCII значення символу '" + character + "' = " + asciiValue);
    }
}