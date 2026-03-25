package lab4;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

class Product {
    String name;
    double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }
    public double getPrice() { return price; }
    public String getName() { return name; }
}

public class task8 {
    public static void main(String[] args) { new task8().run(); }

    public void run() {
        System.out.println("\nЗавдання 8: Другий найдорожчий продукт");
        List<Product> products = Arrays.asList(
                new Product("Телефон", 800),
                new Product("Ноутбук", 1500),
                new Product("Мишка", 50)
        );

        Optional<String> secondMostExpensive = products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .skip(1)
                .findFirst()
                .map(Product::getName);

        System.out.println("Продукт: " + secondMostExpensive.orElse("Не знайдено"));
    }
}