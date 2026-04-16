package lab3;

import java.util.Arrays;
import java.util.List;

class Pair<T, U> {
    T first;
    U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public boolean isEqual(Pair<T, U> otherPair) {
        return this.first.equals(otherPair.first) && this.second.equals(otherPair.second);
    }

    @Override
    public String toString() {
        return "[" + first + ", " + second + "]";
    }
}

public class task5 {
    public static void main(String[] args) {
        new task5().run();
    }

    public void run() {
        System.out.println("\nЗавдання 5: Generic Pair<T, U>");

        Pair<Integer, String> p1 = new Pair<>(1, "One");
        Pair<Integer, String> p2 = new Pair<>(1, "One");
        System.out.println("Пара 1: " + p1);
        System.out.println("Пари p1 і p2 однакові? " + p1.isEqual(p2));

        Pair<String, List<Integer>> p3 = new Pair<>("Numbers", Arrays.asList(1, 2, 3));
        System.out.println("Складна пара: " + p3);
    }
}