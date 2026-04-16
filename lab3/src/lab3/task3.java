package lab3;

class Box<T> {
    private T item;

    public void put(T item) {
        this.item = item;
    }

    public T get() {
        return item;
    }
}

public class task3 {
    public static void main(String[] args) {
        new task3().run();
    }

    public void run() {
        System.out.println("\nЗавдання 3: Generic Box<T>");

        Box<Integer> intBox = new Box<>();
        intBox.put(67);
        System.out.println("В коробці лежить Integer: " + intBox.get());

        Box<String> strBox = new Box<>();
        strBox.put("біба-боба");
        System.out.println("В коробці лежить String: " + strBox.get());
    }
}