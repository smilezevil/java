package lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class task5 {
    public static void main(String[] args) { new task5().run(); }

    public void run() {
        System.out.println("\nЗавдання 5: Добуток непарних чисел");
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        Optional<Integer> product = numbers.stream()
                .filter(n -> n % 2 != 0)
                .reduce((a, b) -> a * b);

        System.out.println("Добуток: " + product.orElse(0));
    }
}