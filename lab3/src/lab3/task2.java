package lab3;

import java.util.*;

public class task2 {
    public static void main(String[] args) {
        new task2().run();
    }

    public void run() {
        System.out.println("\nЗавдання 2: Унікальні елементи та підрахунок");
        List<String> items = Arrays.asList("apple", "banana", "apple", "orange", "banana", "apple");

        System.out.println("Оригінальний список: " + items);
        System.out.println("Унікальні об'єкти: " + getUniqueElements(items));
        System.out.println("Підрахунок входжень: " + countOccurrences(items));
    }

    public <T> Set<T> getUniqueElements(List<T> list) {
        return new HashSet<>(list);
    }

    public <T> Map<T, Integer> countOccurrences(List<T> list) {
        Map<T, Integer> counts = new HashMap<>();
        for (T item : list) {
            counts.put(item, counts.getOrDefault(item, 0) + 1);
        }
        return counts;
    }
}