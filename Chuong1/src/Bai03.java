
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
public class Bai03 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b;
        System.out.print("Nhập a: ");
        a = scanner.nextInt();
        System.out.print("Nhập b: ");
        b = scanner.nextInt();
        if (a != b) {
            if (a > b) {
                System.out.println(a + " lớn hơn " + b);
            } else {
                System.out.println(b + " lớn hơn " + a);
            }
        }
        else System.out.println("2 số bằng nhau");

    }
}
