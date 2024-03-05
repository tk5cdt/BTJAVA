package Bai31;

import java.util.Scanner;

public class Main {
    public static void menu() {
        System.out.println("-------------------MENU-------------------");
        System.out.println("0. Thoat");
        System.out.println("1. Nhap DS BDS");
        System.out.println("2. Xuat DS BDS");
        System.out.println("3. Tong phi kinh doanh");
        System.out.println("4. Xuat cac BDS tinh phi");
    }

    public static void main(String[] args) {
        DSBatDongSan ds = new DSBatDongSan();
        Scanner scanner = new Scanner(System.in);
        int chon;
        do {
            menu();
            do {
                System.out.print("Chon chuc nang muon thuc hien: ");
                chon = scanner.nextInt();
            } while (chon < 0 || chon > 3);

            switch (chon) {
                case 0: {
                    System.out.println("Bye!");
                    break;
                }
                case 1:{
                    ds.nhap();
                    break;
                }
                case 2: {
                    ds.xuat();
                    break;
                }
                case 3: {
                    double totalFee = ds.TongGiaTri();
                    System.out.printf("Total fee: %.2f\n", totalFee);
                    break;
                }
                case 4: {
                    double totalTax = ds.TongThue();
                    System.out.printf("Total tax: %.2f\n", totalTax);
                    break;
                }
            }
        } while (chon != 0);
    }
}

