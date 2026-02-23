public class task4 {
    public void run() {
        System.out.println("\nЗавдання 4:");
        int limit = 55;

        int first = 0, second = 1;
        System.out.print("Ряд Фібоначчі до " + limit + ": ");

        while (first <= limit) {
            System.out.print(first + " ");
            int next = first + second;
            first = second;
            second = next;
        }
        System.out.println();
    }
}