
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
public class Bai04 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int a, b, c;
        System.out.print("Nhập a: ");
        a = scanner.nextInt();
        System.out.print("Nhập b: ");
        b = scanner.nextInt();
        System.out.print("Nhập c: ");
        c = scanner.nextInt();
        
        if (a != b || b != c || a != c) {
            int max = a>b?a:b;
            max = max>c?max:c;
            System.out.println("Số lớn nhất: " + max);
            int min = a<b?a:b;
            min = min<c?min:c;
            System.out.println("Số nhỏ nhất: " + min);
        }
        else System.out.println("3 số bằng nhau");
    }
}
