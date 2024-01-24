
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
public class Bai08 {
    public static void main(String[] args) {
        float diem;
        Scanner s = new Scanner(System.in);
        do {
            System.out.print("Nhập điểm (Số thực từ 0 đến 10): ");
            diem = Float.parseFloat(s.nextLine());
        } while (diem < 0 || diem > 10);
        System.out.print("Điểm chữ: ");
        if(diem >= 8.5)
            System.out.println("A");
        else if(diem >= 7)
            System.out.println("B");
        else if(diem >= 5.5)
            System.out.println("C");
        else if(diem >= 4)
            System.out.println("D");
        else System.out.println("F");
    }
}
