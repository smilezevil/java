package lab3;

import java.util.HashMap;
import java.util.Map;

class Student {
    String name;
    int course;

    public Student(String name, int course) {
        this.name = name;
        this.course = course;
    }

    @Override
    public String toString() {
        return name + " (Курс: " + course + ")";
    }
}

public class task1 {
    public static void main(String[] args) {
        new task1().run();
    }

    private Map<Integer, Student> registry = new HashMap<>();

    public void run() {
        System.out.println("\nЗавдання 1: Реєстр студентів");
        addStudent(1, new Student("Іван", 2));
        addStudent(2, new Student("Марія", 3));
        addStudent(3, new Student("Дмитро", 2));

        displayAll();

        System.out.println("Пошук ID 3: " + searchStudent(3));

        removeStudent(1);
        displayAll();
    }

    public void addStudent(int id, Student student) {
        registry.put(id, student);
    }

    public void removeStudent(int id) {
        registry.remove(id);
    }

    public Student searchStudent(int id) {
        return registry.get(id);
    }

    public void displayAll() {
        System.out.println("Реєстр студентів: " + registry);
    }
}