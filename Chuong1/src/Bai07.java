
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
public class Bai07 {

    public static void main(String[] args) {
        int thang;
        Scanner s = new Scanner(System.in);
        do {
            System.out.print("Nhập vào tháng để biết số ngày (số nguyên > 0 và < 13): ");
            thang = s.nextInt();
        } while (thang <= 0 || thang > 12);
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                System.out.println("Tháng " + thang + " có 31 ngày");
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                System.out.println("Tháng " + thang + " có 30 ngày");
                break;
            case 2:
                int nam;
                do {
                    System.out.print("Nhập năm (số nguyên > 0): ");
                    nam = s.nextInt();
                } while (nam <= 0);
                if ((nam % 4 == 0 || nam % 100 == 0) && nam % 100 != 0) {
                    System.out.println("Tháng 2 năm " + nam + " có 29 ngày");
                } else {
                    System.out.println("Tháng 2 năm " + nam + " có 28 ngày");
                }
        }

    }
}
