
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
public class Bai12 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        do {
            System.out.print("Nhập n (số nguyên > 0): ");
            n = s.nextInt();
        } while (n <= 0);
        int dem = 0;
        for (int i = 2; i <= n/2; i++) {
            if (n % i == 0) {
                dem++;
                System.out.print(i + " ");
            }
        }
        System.out.println("\nSố lượng ước của " + n + ": " + dem);
    }
}
