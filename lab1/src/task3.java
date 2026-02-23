public class task3 {
    public void run() {
        System.out.println("\nЗавдання 3:");
        char grade = '5';

        switch (grade) {
            case '5':
                System.out.println("Оцінка " + grade + ": Відмінно!");
                break;
            case '4':
                System.out.println("Оцінка " + grade + ": Добре!");
                break;
            case '3':
                System.out.println("Оцінка " + grade + ": Задовільно.");
                break;
            case '2':
                System.out.println("Оцінка " + grade + ": Мінімально прохідний бал.");
                break;
            case '1':
                System.out.println("Оцінка " + grade + ": Незадовільно.");
                break;
            default:
                System.out.println("Невідома оцінка.");
        }
    }
}