package Bai32;


import java.util.Scanner;

public class GVCoHuu extends LDBienChe {
    private double hsThamNien;

    public GVCoHuu(String maSo, String hoTen, int namSinh, double HSL, double LCS, int hsThamNien) {
        super(maSo, hoTen, namSinh, HSL, LCS);
        this.hsThamNien = hsThamNien;
    }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập hệ số thâm niên: ");
        hsThamNien = sc.nextDouble();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("Hệ số thâm niên: %d\n", hsThamNien);
    }

    @Override
    public double tinhLuong() {
        return super.tinhLuong() *(1 + hsThamNien) ;
    }
}