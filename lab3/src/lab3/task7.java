package lab3;

import java.util.ArrayList;
import java.util.List;

public class task7 {
    public static void main(String[] args) {
        new task7().run();
    }

    public void run() {
        System.out.println("\nЗавдання 7: Lower-bounded wildcard");

        List<Integer> intList = new ArrayList<>();
        List<Number> numList = new ArrayList<>();

        addToList(intList);
        addToList(numList);

        System.out.println("Integer List: " + intList);
        System.out.println("Number List: " + numList);
    }

    public void addToList(List<? super Integer> list) {
        for (int i = 1; i <= 10; i++) {
            list.add(i);
        }
    }
}