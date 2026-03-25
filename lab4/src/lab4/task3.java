package lab4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class task3 {
    public static void main(String[] args) { new task3().run(); }

    public void run() {
        System.out.println("\nЗавдання 3: Найдовше ім'я");
        List<String> names = Arrays.asList("Катерина", "Анастасія", "Іван");

        Optional<String> longestName = names.stream()
                .max(Comparator.comparingInt(String::length));

        System.out.println("Найдовше ім'я: " + longestName.orElse("Список порожній"));
    }
}