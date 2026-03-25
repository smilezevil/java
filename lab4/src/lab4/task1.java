package lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class task1 {
    public static void main(String[] args) { new task1().run(); }

    public void run() {
        System.out.println("\nЗавдання 1: Рядок на «Х»");
        List<String> words = Arrays.asList("Яблуко", "Хліб", "Холодильник", "Дім");

        Optional<String> result = words.stream()
                .filter(s -> s.startsWith("Х") && s.length() > 5)
                .findFirst()
                .or(() -> Optional.of("Default"));

        System.out.println("Результат: " + result.get());
    }
}