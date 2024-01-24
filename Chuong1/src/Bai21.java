
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
public class Bai21 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int h = 0;
        do {
            System.out.print("Nhập chiều cao của tam giác cân (số nguyên lớn hơn 0): ");
            h = s.nextInt();
        } while (h < 0);
        int dai = 0;
        while (h > 0) {
            for (int i = 1; i < h; i++) {
                System.out.print(' ');
            }
            for (int k = 0; k < dai; k++) {
                if (k == 0 || k == dai - 1) {
                    System.out.print('*');
                } else {
                    System.out.print(' ');
                }
            }
            h--;
            dai += 2;
            System.out.print("\n");
        }
    }
}
