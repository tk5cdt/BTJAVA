
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
public class Bai11 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);

        int ngay = 0, thang = 0, nam = 0;

        do {
            System.out.print("Nhập năm (số nguyên > 0): ");
            nam = s.nextInt();
        } while (nam <= 0);
        do {
            System.out.print("Nhập tháng (số nguyên > 0 và < 13): ");
            thang = s.nextInt();
        } while (thang <= 0 || thang > 12);

        int soNgayTrongThang = 0;
        switch (thang) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                do {
                    System.out.print("Nhập ngày (số nguyên từ 1 đến 31): ");
                    ngay = s.nextInt();
                } while (ngay <= 0 || ngay > 31);
                soNgayTrongThang = 31;
                break;
            case 4:
            case 6:
            case 9:
            case 11:
                do {
                    System.out.print("Nhập ngày (số nguyên từ 1 đến 30): ");
                    ngay = s.nextInt();
                } while (ngay <= 0 || ngay > 30);
                soNgayTrongThang = 30;
                break;
            case 2:
                if ((nam % 4 == 0 || nam % 100 == 0) && nam % 100 != 0) {
                    do {
                        System.out.print("Nhập ngày (số nguyên từ 1 đến 29): ");
                        ngay = s.nextInt();
                    } while (ngay <= 0 || ngay > 29);
                    soNgayTrongThang = 29;
                } else {
                    do {
                        System.out.print("Nhập ngày (số nguyên từ 1 đến 28): ");
                        ngay = s.nextInt();
                    } while (ngay <= 0 || ngay > 28);
                    soNgayTrongThang = 28;
                }
                break;
        }
        System.out.println("Ngày vừa nhập: "+ ngay + "/" + thang + "/" + nam);
        ngay--;
	if (ngay == 0)
	{
		thang--;
		if (thang == 0)
		{
			thang = 12;
			nam--;
		}
		ngay = soNgayTrongThang;
	}
        
        System.out.println("Ngày hôm trước: "+ ngay++ + "/" + thang + "/" + nam);
        
        ngay++;
	if (ngay > soNgayTrongThang)
	{
		ngay = 1;
		thang++;
		if (thang > 12)
		{
			thang = 1;
			nam++;
		}
	}
        System.out.println("Ngày hôm sau: "+ ngay + "/" + thang + "/" + nam);
    }
}
