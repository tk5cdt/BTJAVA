
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
public class Bai13 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        do {
            System.out.print("Nhập n (số nguyên > 0): ");
            n = s.nextInt();
        } while (n <= 0);
        System.out.println("Các ước số lẻ của " + n + ": ");
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0 && i % 2 == 1) {
                System.out.print(i + " ");
            }
        }
        System.out.println("");
    }
}
