package lab4;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class task9 {
    public static void main(String[] args) { new task9().run(); }

    public void run() {
        System.out.println("\nЗавдання 9: Назви продуктів із Map");
        Map<Integer, Optional<String>> map = new HashMap<>();
        map.put(1, Optional.of("Стіл"));
        map.put(2, Optional.empty());
        map.put(3, Optional.of("Крісло"));

        List<String> validNames = map.values().stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .map(String::toUpperCase)
                .collect(Collectors.toList());

        System.out.println("Назви: " + validNames);
    }
}