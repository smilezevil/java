package lab2;

import java.util.LinkedList;
import java.util.Queue;

public class Task2 {
  public static void main(String[] args) {
    new Task2().run();
  }
  public void run() {
    System.out.println("\nЗавдання 2: Колекції");

    // Використовуємо інтерфейс Queue та реалізацію LinkedList для черги FIFO
    Queue<String> customerQueue = new LinkedList<>();

    System.out.println("Додаємо запити до черги...");
    customerQueue.add("Запит #1: Відновлення пароля");
    customerQueue.add("Запит #2: Проблема з оплатою");
    customerQueue.add("Запит #3: Консультація щодо товару");

    System.out.println("Поточна черга: " + customerQueue);
    System.out.println("Починаємо обробку...\n");

    // Обробляємо запити: вилучаємо по одному, поки черга не стане порожньою
    while (!customerQueue.isEmpty()) {
      // poll() дістає перший елемент і ВИДАЛЯЄ його з черги
      String currentRequest = customerQueue.poll();
      System.out.println("Обробляється: [" + currentRequest + "]");
    }

    System.out.println("\nУсі запити оброблено. Чи порожня черга зараз? " + customerQueue.isEmpty());
  }
}