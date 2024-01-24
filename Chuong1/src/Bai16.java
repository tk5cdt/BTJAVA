
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
public class Bai16 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int n;
        do {
            System.out.print("Nhập n (số nguyên > 0): ");
            n = s.nextInt();
        } while (n <= 0);
        int dem = 0;
        int temp = n;
        while (temp > 0) {
            if (temp % 10 % 2 != 0) {
                dem++;
            }
            temp /= 10;
        }
        System.out.println("Số lượng chữ số lẻ của số " + n + ": " + dem);
    }
}
