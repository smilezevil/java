package lab2;

public class Task1 {
    public static void main(String[] args) {
        new Task1().run();
    }
    public void run() {
        System.out.println("Завдання 1: Обробка винятків");

        // Тестуємо правильні дані
        System.out.println("Спроба входу з правильними даними:");
        loginSystem("admin", "qwerty");

        System.out.println("\nСпроба входу з неправильними даними:");
        // Тестуємо неправильні дані, щоб викликати Exception
        loginSystem("guest", "12345");
    }

    public void loginSystem(String username, String password) {
        String correctUsername = "admin";
        String correctPassword = "qwerty";

        try {
            if (!username.equals(correctUsername) || !password.equals(correctPassword)) {
                // Викидаємо Exception, якщо дані невірні
                throw new InvalidCredentialsException("Неправильне ім'я користувача або пароль для: " + username);
            }
            System.out.println("Успішний вхід! Вітаємо, " + username + ".");

        } catch (InvalidCredentialsException e) {
            // Перехоплюємо Exception і відображаємо повідомлення
            System.out.println("Помилка авторизації: " + e.getMessage());
        }
    }
}