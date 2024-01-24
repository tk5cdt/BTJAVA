
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
public class Bai06 {

    public static void main(String[] args) {
        int nam;
        Scanner s = new Scanner(System.in);
        do {
            System.out.print("Nhập vào năm để kiểm tra nhuận (số nguyên > 0): ");
            nam = s.nextInt();
        } while (nam <= 0);
        if ((nam % 4 == 0 || nam % 100 == 0) && nam % 100 != 0) {
            System.out.println("Năm nhuần");
        }
        else System.out.println("Năm không nhuần");
    }
}
