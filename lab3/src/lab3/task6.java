package lab3;

import java.util.Arrays;
import java.util.List;

abstract class Shape {
    abstract double getArea();
}

class Circle extends Shape {
    double radius;
    Circle(double radius) { this.radius = radius; }
    @Override double getArea() { return Math.PI * radius * radius; }
}

class Rectangle extends Shape {
    double width, height;
    Rectangle(double w, double h) { width = w; height = h; }
    @Override double getArea() { return width * height; }
}

public class task6 {
    public static void main(String[] args) {
        new task6().run();
    }

    public void run() {
        System.out.println("\nЗавдання 6: Upper-bounded wildcard");
        List<Circle> circles = Arrays.asList(new Circle(2.0), new Circle(3.0));
        List<Rectangle> rectangles = Arrays.asList(new Rectangle(2, 4), new Rectangle(3, 3));

        System.out.println("Загальна площа кіл: " + calculateTotalArea(circles));
        System.out.println("Загальна площа прямокутників: " + calculateTotalArea(rectangles));
    }

    public double calculateTotalArea(List<? extends Shape> shapes) {
        double total = 0;
        for (Shape shape : shapes) {
            total += shape.getArea();
        }
        return total;
    }
}