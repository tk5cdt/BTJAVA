
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
public class Bai14 {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        boolean flag = true;
        
        int n;
        do {
            System.out.print("Nhập n (số nguyên > 0): ");
            n = s.nextInt();
        } while (n <= 0);
        if(n <= 1)
            flag = false;
        for(int i = 2; i<=n/2; i++)
        {
            if(n%i==0)
                flag = false;
        }
        if(flag)
            System.out.println(n + " là số nguyên tố");
        else System.out.println(n + " không phải số nguyên tố");
        
    }
}
