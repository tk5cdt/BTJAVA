
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
public class Bai05 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, c;
        System.out.println("Giải phương trình ax^2 + bx + c = 0");
        System.out.print("Nhập a: ");
        a = scanner.nextInt();
        System.out.print("Nhập b: ");
        b = scanner.nextInt();
        System.out.print("Nhập c: ");
        c = scanner.nextInt();

        if (a == 0) {
            if (b == 0) {
                if (c == 0) {
                    System.out.println("Phương trình vô số nghiệm");
                } else {
                    System.out.println("Phương trình vô nghiệm");
                }
            } else {
                System.out.println("Phương trình có nghiệm x = " + (-c / b));
            }
        } else {
            float delta = b * b - 4 * a * c;
            if (delta > 0) {
                System.out.println("Phương trình có 2 nghiệm phân biệt");
                System.out.println("x1 = " + (-b + Math.sqrt(delta)) / (2 * a));
                System.out.println("x2 = " + (-b - Math.sqrt(delta)) / (2 * a));
            } else if (delta == 0) {
                System.out.println("Phương trình có nghiệm kép x = " + -b / (2 * a));
            } else {
                System.out.println("Phương trình vô nghiệm");
            }
        }
    }
}
