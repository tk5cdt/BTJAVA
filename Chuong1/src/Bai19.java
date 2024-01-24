
import java.util.Scanner;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrator
 */
public class Bai19 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int m = 0, n = 0;
        do {
            System.out.print("Nhập chiều dài (số nguyên lớn hơn 0): ");
            m = s.nextInt();
        } while (m < 0);
        do {
            System.out.print("Nhập chiều rộng (số nguyên lớn hơn 0): ");
            n = s.nextInt();
        } while (n < 0);
        for (int i = 0; i < n; i++) {
            if (i == 0 || i == n - 1) {
                for (int j = 0; j < m; j++) {
                    System.out.print("* ");
                }
            }
            else
            {
                System.out.print("* ");
                for (int j = 1; j < m-1; j++) {
                    System.out.print("  ");
                }
                System.out.print("* ");
            }

            System.out.println("");
        }
    }
}
