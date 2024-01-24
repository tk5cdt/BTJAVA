
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
public class Bai15 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        float sum = 0;
        float n;
        System.out.println("Nhập từng số thực để tính tổng, nhập 0 để dừng: ");
        do {
            n = Float.parseFloat(s.nextLine());
            sum += n;
        } while (n != 0);
        System.out.println("Tổng các số vừa nhập: " + sum);
    }
}
