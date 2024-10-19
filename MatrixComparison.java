import java.util.Scanner;

public class MatrixComparison {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Введення розміру матриць
        System.out.print("Введіть розмір матриць n (n <= 15): ");
        int n = 0;

        // Перевірка коректності введення розміру матриці
        try {
            n = scanner.nextInt();
            if (n <= 0 || n > 15) {
                throw new IllegalArgumentException("Розмір матриці повинен бути в діапазоні від 1 до 15.");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        } catch (Exception e) {
            System.out.println("Помилка: некоректний ввід. Будь ласка, введіть ціле число.");
            return;
        }

        int[][] A = new int[n][n];
        int[][] B = new int[n][n];
        int[] X = new int[n];

        // Введення елементів першої матриці A
        System.out.println("Введіть елементи матриці A:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                A[i][j] = readMatrixElement(scanner, i, j, "A");
            }
        }

        // Введення елементів другої матриці B
        System.out.println("Введіть елементи матриці B:");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
