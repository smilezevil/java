package lab4;

import java.util.*;

public class task10 {
    public static void main(String[] args) { new task10().run(); }

    public void run() {
        System.out.println("\nЗавдання 10: Місто з найвищою середньою температурою");
        Map<String, List<Integer>> cityTemps = new HashMap<>();
        cityTemps.put("Львів", Arrays.asList(15, 18, 20));
        cityTemps.put("Київ", Arrays.asList(20, 25, 23));
        cityTemps.put("Одеса", Arrays.asList(25, 28, 30));

        Optional<String> hottestCity = cityTemps.entrySet().stream()
                .max(Comparator.comparingDouble(entry ->
                        entry.getValue().stream().mapToInt(Integer::intValue).average().orElse(0.0)
                ))
                .map(Map.Entry::getKey);

        System.out.println("Найспекотніше місто: " + hottestCity.orElse("Немає даних"));
    }
}