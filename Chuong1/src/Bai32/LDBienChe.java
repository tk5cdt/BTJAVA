package Bai32;

import java.util.Scanner;

public class LDBienChe extends NguoiLaoDong {
    private double HSL;
    private double LCS;

    public LDBienChe(String maSo, String hoTen, int namSinh, double HSL, double LCS) {
        super(maSo, hoTen, namSinh);
        this.HSL = HSL;
        this.LCS = LCS;
    }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập hệ số lương: ");
        HSL = sc.nextDouble();
        System.out.print("Nhập lương cơ sở: ");
        LCS = sc.nextDouble();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("Hệ số lương: %,.2f\n", HSL);
        System.out.printf("Lương cơ sở: %,.2f\n", LCS);
    }

    @Override
    public double tinhLuong() {
        return HSL *  LCS;
    }
}
