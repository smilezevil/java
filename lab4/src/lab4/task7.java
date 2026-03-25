package lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

class Transaction {
    double amount;
    String category;

    public Transaction(double amount, String category) {
        this.amount = amount;
        this.category = category;
    }
    public String getCategory() { return category; }
    public double getAmount() { return amount; }
}

public class task7 {
    public static void main(String[] args) { new task7().run(); }

    public void run() {
        System.out.println("\nЗавдання 7: Сума транзакцій за категоріями");
        List<Transaction> transactions = Arrays.asList(
                new Transaction(150, "Продукти"),
                new Transaction(300, "Одяг"),
                new Transaction(50, "Продукти")
        );

        Map<String, Double> sumsByCategory = transactions.stream()
                .collect(Collectors.groupingBy(
                        Transaction::getCategory,
                        Collectors.summingDouble(Transaction::getAmount)
                ));

        System.out.println("Суми: " + sumsByCategory);
    }
}