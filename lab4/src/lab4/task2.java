package lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class task2 {
    public static void main(String[] args) { new task2().run(); }

    public void run() {
        System.out.println("\nЗавдання 2: Ігнорування порожніх Optionals");
        List<Optional<Integer>> optionals = Arrays.asList(
                Optional.of(10), Optional.empty(), Optional.of(20)
        );

        List<Integer> numbers = optionals.stream()
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        System.out.println("Чисті числа: " + numbers);
    }
}