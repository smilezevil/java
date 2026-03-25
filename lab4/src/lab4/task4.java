package lab4;

import java.util.*;
import java.util.stream.Collectors;

class Employee {
    String name;
    double salary;

    public Employee(String name, double salary) {
        this.name = name;
        this.salary = salary;
    }
    public double getSalary() { return salary; }
    @Override public String toString() { return name + " (" + salary + ")"; }
}

public class task4 {
    public static void main(String[] args) { new task4().run(); }

    public void run() {
        System.out.println("\nЗавдання 4: Групування за зарплатою");
        List<Employee> employees = Arrays.asList(
                new Employee("Катя", 2500), new Employee("Максім", 2800),
                new Employee("Ваня", 4500), new Employee("Міша", 4000),
                new Employee("Настя", 6000)
        );

        Map<String, Optional<Employee>> grouped = employees.stream()
                .collect(Collectors.groupingBy(e -> {
                    if (e.salary < 3000) return "< 3000";
                    else if (e.salary <= 5000) return "3000-5000";
                    else return "> 5000";
                }, Collectors.maxBy(Comparator.comparingDouble(Employee::getSalary))));

        grouped.forEach((range, emp) -> System.out.println("Діапазон " + range + ": " + emp.get()));
    }
}