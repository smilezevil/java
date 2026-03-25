package lab4;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Person {
    String name;
    List<Person> friends;

    public Person(String name, List<Person> friends) {
        this.name = name;
        this.friends = friends;
    }
}

public class task6 {
    public static void main(String[] args) { new task6().run(); }

    public void run() {
        System.out.println("\nЗавдання 6: Унікальні імена друзів");
        Person friend1 = new Person("Катя", Arrays.asList());
        Person friend2 = new Person("Ваня", Arrays.asList());

        List<Person> people = Arrays.asList(
                new Person("Максім", Arrays.asList(friend1, friend2)),
                new Person("Міша", Arrays.asList(friend1))
        );

        List<String> uniqueFriends = people.stream()
                .flatMap(p -> p.friends.stream())
                .map(f -> f.name.toUpperCase())
                .distinct()
                .collect(Collectors.toList());

        System.out.println("Унікальні друзі: " + uniqueFriends);
    }
}