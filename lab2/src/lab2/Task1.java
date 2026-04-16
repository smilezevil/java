package lab2;

public class Task1 {
    public static void main(String[] args) {
        new Task1().run();
    }
    public void run() {
        System.out.println("Завдання 1: Обробка винятків");

        System.out.println("Спроба входу з правильними даними:");
        loginSystem("smilezevil", "smile0110");

        System.out.println("\nСпроба входу з неправильними даними:");
        loginSystem("abobus", "12345");
    }

    public void loginSystem(String username, String password) {
        String correctUsername = "smilezevil";
        String correctPassword = "smile0110";

        try {
            if (!username.equals(correctUsername) || !password.equals(correctPassword)) {
                throw new InvalidCredentialsException("Неправильне ім'я користувача або пароль для: " + username);
            }
            System.out.println("Успішний вхід! Вітаємо, " + username + ".");

        } catch (InvalidCredentialsException e) {
            System.out.println("Помилка авторизації: " + e.getMessage());
        }
    }
}