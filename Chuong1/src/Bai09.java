
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
public class Bai09 {

    public static void main(String[] args) {
        int chiSoCu, chiSoMoi;
        Scanner s = new Scanner(System.in);
        do {
            System.out.print("Nhập chỉ số điện cũ (Số nguyên >= 0): ");
            chiSoCu = s.nextInt();
        } while (chiSoCu < 0);
        do {
            System.out.print("Nhập chỉ số điện mới (Số nguyên >= chỉ số điện cũ): ");
            chiSoMoi = s.nextInt();
        } while (chiSoMoi < chiSoCu);
        int soTien, soDien = chiSoMoi - chiSoCu;
        System.out.println("Số kWh sử dụng trong tháng: " + soDien);
        System.out.print("Số tiền điện phải trả: ");
        if (soDien <= 50) {
            soTien = soDien * 1480;
        } else if (soDien <= 100) {
            soTien = (soDien-50) * 1533 + 50 * 1480;
        } else if (soDien <= 200) {
            soTien = (soDien-100) * 1786 + 100 * 1533 + 50 * 1480;
        } else if (soDien <= 300) {
            soTien = (soDien-200) * 2242 + 100 * 1786 + 100 * 1533 + 50 * 1480;
        } else {
            soTien = (soDien - 300) * 2503 + 100 * 2242 + 100 * 1786 + 100 * 1533 + 50 * 1480;
        }
        System.out.println(soTien);
    }

}
