public class task5 {
    public void run() {
        System.out.println("\nЗавдання 5:");

        int[][] matrix = {
                {5, 2, 3},
                {4, 5, 6},
                {7, 8, 9}
        };

        int sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            sum += matrix[i][i];
        }


        System.out.println("Сума елементів головної діагоналі: " + sum);
    }
}