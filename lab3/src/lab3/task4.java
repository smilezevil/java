package lab3;

public class task4 {
    public static void main(String[] args) {
        new task4().run();
    }

    public void run() {
        System.out.println("\nЗавдання 4: Generic method findMax");

        Integer[] intArr = {1, 5, 3, 9, 2};
        Double[] doubleArr = {2.5, 7.1, 1.0, 9.9};
        Character[] charArr = {'a', 'z', 'k'};
        String[] strArr = {"Apple", "Zebra", "Mango"};

        System.out.println("Max Integer: " + findMax(intArr));
        System.out.println("Max Double: " + findMax(doubleArr));
        System.out.println("Max Character: " + findMax(charArr));
        System.out.println("Max String: " + findMax(strArr));
    }

    public <T extends Comparable<T>> T findMax(T[] array) {
        T max = array[0];
        for (T element : array) {
            if (element.compareTo(max) > 0) {
                max = element;
            }
        }
        return max;
    }
}