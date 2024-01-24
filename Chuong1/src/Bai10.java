
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
public class Bai10 {

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int ngay, thang, nam;
        boolean flag = true;

        System.out.print("Nhập ngày: ");
        ngay = s.nextInt();
        System.out.print("Nhập tháng: ");
        thang = s.nextInt();
        System.out.print("Nhập năm: ");
        nam = s.nextInt();

        if (nam > 0) {
            if (thang > 12 || thang < 1)
                ; else {
                switch (thang) {
                    case 1:
                    case 3:
                    case 5:
                    case 7:
                    case 8:
                    case 10:
                    case 12:
                        if (!(ngay <= 31 && ngay >= 1)) {
                            flag = false;
                        }
                        break;

                    case 4:
                    case 6:
                    case 9:
                    case 11:
                        if (!(ngay <= 30 && ngay >= 1)) {
                            flag = false;
                        }
                        break;
                    default:
                        if ((nam % 4 == 0 || nam % 100 == 0) && nam % 100 != 0) {
                            if (!(ngay <= 29 && ngay >= 1)) {
                                flag = false;
                            }
                        } else {
                            if (!(ngay <= 28 && ngay >= 1)) {
                                flag = false;
                            }
                        }
                        break;
                }
            }
        } else {
            flag = false;
        }
        if (flag) {
            System.out.println(ngay + "/" + thang + "/" + nam + " hợp lệ");
        } else {
            System.out.println(ngay + "/" + thang + "/" + nam + " không hợp lệ");
        }
    }
}
