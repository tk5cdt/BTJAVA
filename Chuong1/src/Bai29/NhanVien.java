package Bai29;

import java.util.Scanner;

public class NhanVien {
    protected String hoTen;
    protected int namVaoLam;
    protected static int luongCB = 1490000;

    public NhanVien(String hoTen, int namVaoLam) {
        this.hoTen = hoTen;
        this.namVaoLam = namVaoLam;
    }

    public NhanVien() {
        this.hoTen = "";
        this.namVaoLam = 0;
    }

    public void nhapThongTin()
    {
        Scanner sc = new Scanner(System.in);

        System.out.print("Họ tên: ");
        hoTen = sc.nextLine();
        System.out.print("Năm vào làm: ");
        namVaoLam = sc.nextInt();
    }

    public void xuatThongTin()
    {
        System.out.println("Họ tên: "+hoTen);
        System.out.println("Năm vào làm: "+namVaoLam);
    }

    public int tinhLuong() {
        return luongCB;
    }

}
