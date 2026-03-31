package com.hotel;

import com.hotel.dao.*;
import com.hotel.model.*;
import com.hotel.util.AuthService;
import com.hotel.util.DBConnection;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        System.out.println("=== Система управління готелем ===");

        if (!authMenu()) {
            System.out.println("Доступ заборонено. Виходимо...");
            return;
        }

        boolean running = true;
        while (running) {
            System.out.println("\n=== ГОЛОВНЕ МЕНЮ ===");
            System.out.println("1. Клієнти");
            System.out.println("2. Типи номерів");
            System.out.println("3. Номери");
            System.out.println("4. Бронювання");
            System.out.println("5. Оплати");
            System.out.println("6. Рейтинги номерів");
            System.out.println("0. Вихід");
            System.out.print("Оберіть: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> clientMenu();
                    case 2 -> roomTypeMenu();
                    case 3 -> roomMenu();
                    case 4 -> reservationMenu();
                    case 5 -> paymentMenu();
                    case 6 -> roomRatingMenu();
                    case 0 -> running = false;
                    default -> System.out.println("Невірний вибір!");
                }
            } catch (Exception e) {
                System.out.println("Помилка: введіть число від 0 до 6!");
            }
        }

        DBConnection.closeConnection();
        System.out.println("До побачення!");
    }

    // ===== АВТОРИЗАЦІЯ =====
    static boolean authMenu() throws Exception {
        while (true) {
            System.out.println("\n1. Увійти");
            System.out.println("2. Зареєструватись");
            System.out.println("0. Вихід");
            System.out.print("Оберіть: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> {
                        System.out.print("Логін: ");
                        String username = scanner.nextLine();
                        System.out.print("Пароль: ");
                        String password = scanner.nextLine();
                        if (AuthService.login(username, password)) {
                            System.out.println("Успішний вхід! Вітаємо, " + username + "!");
                            return true;
                        } else {
                            System.out.println("Невірний логін або пароль!");
                        }
                    }
                    case 2 -> {
                        System.out.print("Новий логін: ");
                        String username = scanner.nextLine();
                        System.out.print("Новий пароль: ");
                        String password = scanner.nextLine();
                        AuthService.register(username, password);
                    }
                    case 0 -> { return false; }
                    default -> System.out.println("Невірний вибір!");
                }
            } catch (Exception e) {
                System.out.println("Помилка: введіть число!");
            }
        }
    }

    // ===== ДОПОМІЖНИЙ МЕТОД ДЛЯ ВВЕДЕННЯ ДАТИ =====
    static LocalDateTime readDate(String prompt) {
        while (true) {
            System.out.print(prompt + " (yyyy-MM-ddTHH:mm, наприклад 2026-04-05T14:00): ");
            try {
                return LocalDateTime.parse(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Невірний формат дати! Спробуйте ще раз.");
            }
        }
    }

    // ===== КЛІЄНТИ =====
    static void clientMenu() {
        try {
            ClientDAO dao = new ClientDAO();
            System.out.println("\n--- КЛІЄНТИ ---");
            System.out.println("1. Показати всіх");
            System.out.println("2. Знайти за ID");
            System.out.println("3. Додати");
            System.out.println("4. Оновити");
            System.out.println("5. Видалити");
            System.out.println("6. Пошук");
            System.out.print("Оберіть: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> dao.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: ");
                    Client c = dao.read(Integer.parseInt(scanner.nextLine()));
                    System.out.println(c != null ? c : "Клієнта не знайдено!");
                }
                case 3 -> {
                    System.out.print("ПІБ: ");
                    String name = scanner.nextLine();
                    System.out.print("Паспортні дані: ");
                    String passport = scanner.nextLine();
                    dao.create(new Client(0, name, passport));
                }
                case 4 -> {
                    System.out.print("ID клієнта: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Нове ПІБ: ");
                    String name = scanner.nextLine();
                    System.out.print("Нові паспортні дані: ");
                    String passport = scanner.nextLine();
                    dao.update(new Client(id, name, passport));
                }
                case 5 -> {
                    System.out.print("ID для видалення: ");
                    dao.delete(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Пошуковий запит: ");
                    dao.search(scanner.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Невірний вибір!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // ===== ТИПИ НОМЕРІВ =====
    static void roomTypeMenu() {
        try {
            RoomTypeDAO dao = new RoomTypeDAO();
            System.out.println("\n--- ТИПИ НОМЕРІВ ---");
            System.out.println("1. Показати всі");
            System.out.println("2. Знайти за ID");
            System.out.println("3. Додати");
            System.out.println("4. Оновити");
            System.out.println("5. Видалити");
            System.out.println("6. Пошук");
            System.out.print("Оберіть: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> dao.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: ");
                    RoomType rt = dao.read(Integer.parseInt(scanner.nextLine()));
                    System.out.println(rt != null ? rt : "Тип не знайдено!");
                }
                case 3 -> {
                    System.out.print("Назва типу: ");
                    String name = scanner.nextLine();
                    System.out.print("Ціна за ніч: ");
                    BigDecimal price = new BigDecimal(scanner.nextLine());
                    dao.create(new RoomType(0, name, price));
                }
                case 4 -> {
                    System.out.print("ID типу: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Нова назва: ");
                    String name = scanner.nextLine();
                    System.out.print("Нова ціна: ");
                    BigDecimal price = new BigDecimal(scanner.nextLine());
                    dao.update(new RoomType(id, name, price));
                }
                case 5 -> {
                    System.out.print("ID для видалення: ");
                    dao.delete(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Пошуковий запит: ");
                    dao.search(scanner.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Невірний вибір!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число або суму!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // ===== НОМЕРИ =====
    static void roomMenu() {
        try {
            RoomDAO dao = new RoomDAO();
            System.out.println("\n--- НОМЕРИ ---");
            System.out.println("1. Показати всі");
            System.out.println("2. Знайти за ID");
            System.out.println("3. Додати");
            System.out.println("4. Оновити");
            System.out.println("5. Видалити");
            System.out.println("6. Пошук");
            System.out.print("Оберіть: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> dao.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: ");
                    Room r = dao.read(Integer.parseInt(scanner.nextLine()));
                    System.out.println(r != null ? r : "Номер не знайдено!");
                }
                case 3 -> {
                    System.out.print("Номер кімнати: ");
                    String number = scanner.nextLine();
                    System.out.print("Місткість: ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID типу: ");
                    int typeId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Колонка: ");
                    String col = scanner.nextLine();
                    dao.create(new Room(0, number, capacity, typeId, col));
                }
                case 4 -> {
                    System.out.print("ID номера: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Новий номер: ");
                    String number = scanner.nextLine();
                    System.out.print("Нова місткість: ");
                    int capacity = Integer.parseInt(scanner.nextLine());
                    System.out.print("Новий ID типу: ");
                    int typeId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Нова колонка: ");
                    String col = scanner.nextLine();
                    dao.update(new Room(id, number, capacity, typeId, col));
                }
                case 5 -> {
                    System.out.print("ID для видалення: ");
                    dao.delete(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Пошуковий запит: ");
                    dao.search(scanner.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Невірний вибір!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // ===== БРОНЮВАННЯ =====
    static void reservationMenu() {
        try {
            ReservationDAO dao = new ReservationDAO();
            System.out.println("\n--- БРОНЮВАННЯ ---");
            System.out.println("1. Показати всі");
            System.out.println("2. Знайти за ID");
            System.out.println("3. Додати");
            System.out.println("4. Оновити");
            System.out.println("5. Видалити");
            System.out.println("6. Пошук");
            System.out.print("Оберіть: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> dao.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: ");
                    Reservation res = dao.read(Integer.parseInt(scanner.nextLine()));
                    System.out.println(res != null ? res : "Бронювання не знайдено!");
                }
                case 3 -> {
                    System.out.print("Кількість гостей: ");
                    int guests = Integer.parseInt(scanner.nextLine());
                    LocalDateTime checkIn = readDate("Дата заїзду");
                    LocalDateTime checkOut = readDate("Дата виїзду");
                    System.out.print("ID клієнта: ");
                    int clientId = Integer.parseInt(scanner.nextLine());
                    System.out.print("ID номера: ");
                    int roomId = Integer.parseInt(scanner.nextLine());
                    dao.create(new Reservation(0, guests, checkIn, checkOut, clientId, roomId));
                }
                case 4 -> {
                    System.out.print("ID бронювання: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Нова кількість гостей: ");
                    int guests = Integer.parseInt(scanner.nextLine());
                    LocalDateTime checkIn = readDate("Нова дата заїзду");
                    LocalDateTime checkOut = readDate("Нова дата виїзду");
                    System.out.print("Новий ID клієнта: ");
                    int clientId = Integer.parseInt(scanner.nextLine());
                    System.out.print("Новий ID номера: ");
                    int roomId = Integer.parseInt(scanner.nextLine());
                    dao.update(new Reservation(id, guests, checkIn, checkOut, clientId, roomId));
                }
                case 5 -> {
                    System.out.print("ID для видалення: ");
                    dao.delete(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Пошуковий запит: ");
                    dao.search(scanner.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Невірний вибір!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // ===== ОПЛАТИ =====
    static void paymentMenu() {
        try {
            PaymentDAO dao = new PaymentDAO();
            System.out.println("\n--- ОПЛАТИ ---");
            System.out.println("1. Показати всі");
            System.out.println("2. Знайти за ID");
            System.out.println("3. Додати");
            System.out.println("4. Оновити");
            System.out.println("5. Видалити");
            System.out.println("6. Пошук");
            System.out.print("Оберіть: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> dao.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: ");
                    Payment p = dao.read(Integer.parseInt(scanner.nextLine()));
                    System.out.println(p != null ? p : "Оплату не знайдено!");
                }
                case 3 -> {
                    System.out.print("Сума: ");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());
                    System.out.print("ID бронювання: ");
                    int resId = Integer.parseInt(scanner.nextLine());
                    dao.create(new Payment(0, amount, resId));
                }
                case 4 -> {
                    System.out.print("ID оплати: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Нова сума: ");
                    BigDecimal amount = new BigDecimal(scanner.nextLine());
                    System.out.print("Новий ID бронювання: ");
                    int resId = Integer.parseInt(scanner.nextLine());
                    dao.update(new Payment(id, amount, resId));
                }
                case 5 -> {
                    System.out.print("ID для видалення: ");
                    dao.delete(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Пошуковий запит: ");
                    dao.search(scanner.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Невірний вибір!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число або суму!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }

    // ===== РЕЙТИНГИ =====
    static void roomRatingMenu() {
        try {
            RoomRatingDAO dao = new RoomRatingDAO();
            System.out.println("\n--- РЕЙТИНГИ НОМЕРІВ ---");
            System.out.println("1. Показати всі");
            System.out.println("2. Знайти за ID");
            System.out.println("3. Додати");
            System.out.println("4. Оновити");
            System.out.println("5. Видалити");
            System.out.println("6. Пошук");
            System.out.print("Оберіть: ");

            int choice = Integer.parseInt(scanner.nextLine());
            switch (choice) {
                case 1 -> dao.readAll().forEach(System.out::println);
                case 2 -> {
                    System.out.print("ID: ");
                    RoomRating rr = dao.read(Integer.parseInt(scanner.nextLine()));
                    System.out.println(rr != null ? rr : "Рейтинг не знайдено!");
                }
                case 3 -> {
                    System.out.print("Рейтинг (0.0 - 10.0): ");
                    float score = Float.parseFloat(scanner.nextLine());
                    if (score < 0 || score > 10) {
                        System.out.println("Рейтинг має бути від 0.0 до 10.0!");
                        return;
                    }
                    System.out.print("ID номера: ");
                    int roomId = Integer.parseInt(scanner.nextLine());
                    dao.create(new RoomRating(0, score, roomId));
                }
                case 4 -> {
                    System.out.print("ID рейтингу: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    System.out.print("Новий рейтинг (0.0 - 10.0): ");
                    float score = Float.parseFloat(scanner.nextLine());
                    if (score < 0 || score > 10) {
                        System.out.println("Рейтинг має бути від 0.0 до 10.0!");
                        return;
                    }
                    System.out.print("Новий ID номера: ");
                    int roomId = Integer.parseInt(scanner.nextLine());
                    dao.update(new RoomRating(id, score, roomId));
                }
                case 5 -> {
                    System.out.print("ID для видалення: ");
                    dao.delete(Integer.parseInt(scanner.nextLine()));
                }
                case 6 -> {
                    System.out.print("Пошуковий запит: ");
                    dao.search(scanner.nextLine()).forEach(System.out::println);
                }
                default -> System.out.println("Невірний вибір!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Помилка: введіть коректне число!");
        } catch (Exception e) {
            System.out.println("Помилка: " + e.getMessage());
        }
    }
}