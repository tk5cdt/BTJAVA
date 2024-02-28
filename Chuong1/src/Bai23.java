package com.example.helloworld;
import java.util.Scanner;
import java.util.Random;
public class Bai23 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Nhập kích thước của mảng
        System.out.print("Nhập số hàng (m <= 50): ");
        int m = scanner.nextInt();
        System.out.print("Nhập số cột (n <= 100): ");
        int n = scanner.nextInt();

        // Kiểm tra ràng buộc về kích thước của mảng
        if (m > 50 || n > 100) {
            System.out.println("Kích thước không hợp lệ. Hãy thử lại.");
            return;
        }

        // Khởi tạo mảng
        int[][] matrix = new int[m][n];

        // Chọn cách nhập hoặc phát sinh giá trị ngẫu nhiên
        System.out.println("Chọn cách nhập giá trị:");
        System.out.println("1. Nhập từ bàn phím");
        System.out.println("2. Phát sinh ngẫu nhiên từ -99 đến 99");
        int choice = scanner.nextInt();

        switch (choice) {
            case 1:
                // Nhập giá trị từ bàn phím
                inputValuesFromConsole(matrix, scanner);
                break;
            case 2:
                // Phát sinh giá trị ngẫu nhiên
                generateRandomValues(matrix);
                break;
            default:
                System.out.println("Lựa chọn không hợp lệ.");
                return;
        }

        // Xuất giá trị các phần tử
        System.out.println("Ma trận vừa nhập/lập là:");
        printMatrix(matrix);

        // Tính tổng giá trị các số lẻ
        int oddSum = sumOfOddNumbers(matrix);
        System.out.println("Tổng các số lẻ trong ma trận là: " + oddSum);

        // Tính tổng các giá trị dương trên dòng k
        System.out.print("Nhập số dòng k (0 <= k < " + m + "): ");
        int k = scanner.nextInt();
        int positiveSumOnRowK = sumOfPositiveNumbersOnRowK(matrix, k);
        System.out.println("Tổng các số dương trên dòng " + k + " là: " + positiveSumOnRowK);

        // Đếm số lượng số dương
        int positiveCount = countPositiveNumbers(matrix);
        System.out.println("Số lượng số dương trong ma trận là: " + positiveCount);

        // Đếm số lượng số nguyên tố trên một dòng của ma trận
        System.out.print("Nhập số dòng để kiểm tra số nguyên tố (0 <= k < " + m + "): ");
        k = scanner.nextInt();
        int primeCountOnRowK = countPrimeNumbersOnRowK(matrix, k);
        System.out.println("Số lượng số nguyên tố trên dòng " + k + " là: " + primeCountOnRowK);

        // Kiểm tra ma trận có tồn tại số âm hay không
        boolean hasNegative = hasNegativeNumbers(matrix);
        System.out.println("Ma trận có tồn tại số âm không? " + (hasNegative ? "Có" : "Không"));

        // Kiểm tra các phần tử trên dòng k có tăng dần từ trái qua phải hay không
        System.out.print("Nhập số dòng để kiểm tra tăng dần (0 <= k < " + m + "): ");
        k = scanner.nextInt();
        boolean isIncreasingOnRowK = isIncreasingOnRowK(matrix, k);
        System.out.println("Các phần tử trên dòng " + k + " có tăng dần từ trái qua phải không? " +
                (isIncreasingOnRowK ? "Có" : "Không"));

        // Liệt kê dòng có chứa số nguyên tố
        System.out.println("Dòng có chứa số nguyên tố:");
        listRowsContainingPrimes(matrix);

        // Liệt kê dòng chứa toàn giá trị chẵn
        System.out.println("Dòng chứa toàn giá trị chẵn:");
        listRowsWithAllEvenNumbers(matrix);
    }

    //hàm nhập giá trị từng phần tử của mảng
    private static void inputValuesFromConsole(int[][] matrix, Scanner scanner) {
        System.out.println("Nhập giá trị từng phần tử của mảng:");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print("Nhập giá trị tại vị trí [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextInt();
            }
        }
    }

    //hàm random giá trị của mảng
    private static void generateRandomValues(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                matrix[i][j] = (int) (Math.random() * 199) - 99;
            }
        }
    }

    //xuất mảng
    private static void printMatrix(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                System.out.print(value + "\t");
            }
            System.out.println();
        }
    }

    //tính tổng giá trị lẻ của mảng
    private static int sumOfOddNumbers(int[][] matrix) {
        int sum = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value % 2 != 0) {
                    sum += value;
                }
            }
        }
        return sum;
    }

    //tính tổng các giá trị dương của mảng trên dòng k
    private static int sumOfPositiveNumbersOnRowK(int[][] matrix, int k) {
        int sum = 0;
        if (k >= 0 && k < matrix.length) {
            for (int value : matrix[k]) {
                if (value > 0) {
                    sum += value;
                }
            }
        }
        return sum;
    }

    //đếm các số dương trong mảng
    private static int countPositiveNumbers(int[][] matrix) {
        int count = 0;
        for (int[] row : matrix) {
            for (int value : row) {
                if (value > 0) {
                    count++;
                }
            }
        }
        return count;
    }

    //đếm số nguyên tố trên dòng k
    private static int countPrimeNumbersOnRowK(int[][] matrix, int k) {
        int count = 0;
        if (k >= 0 && k < matrix.length) {
            for (int value : matrix[k]) {
                if (isPrime(value)) {
                    count += 1;
                }
            }
        }
        return count;
    }

    // check số nguyên tố
    public static boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(number); i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    // kiểm tra mảng có số âm không
    private static boolean hasNegativeNumbers(int[][] matrix) {
        for (int[] row : matrix) {
            for (int value : row) {
                if(value < 0)
                    return true;
            }
        }
        return false;
    }

    // kiểm tra dòng k có phải tăng dần không
    private static boolean isIncreasingOnRowK(int[][] matrix, int k) {
        if (k >= 0 && k < matrix.length) {
            for (int j = 1; j < matrix[k].length; j++) {
                if (matrix[k][j] <= matrix[k][j - 1]) {
                    return false;
                }
            }
        }
        return true;
    }

    // liệt kê các dòng chứa snt
    private static void listRowsContainingPrimes(int[][] matrix) {
        System.out.print("Các dòng có chứa số nguyên tố là: ");
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if(isPrime(matrix[i][j]))
                {
                    System.out.println(i);
                    break;
                }
            }
        }
    }

    // Liệt kê dòng chứa toàn giá trị chẵn
    private static void listRowsWithAllEvenNumbers(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            if(isRowsWithAllEvenNumbers(matrix, i))
            {
                System.out.println(i);
                break;
            }
        }
    }

    public static boolean isRowsWithAllEvenNumbers(int[][] matrix, int k) {
        if (k >= 0 && k < matrix.length) {
            for (int value : matrix[k]) {
                if (value % 2 != 0) {
                    return false;
                }
            }
        }
        return true;
    }

}
