package Bai32;

import java.util.Scanner;

public abstract class LDNgoaiBienChe extends NguoiLaoDong {
    private double mucLuong;

    public LDNgoaiBienChe(String maSo, String hoTen, int namSinh, double mucLuong) {
        super(maSo, hoTen, namSinh);
        this.mucLuong = mucLuong;
    }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mức lương: ");
        mucLuong = sc.nextDouble();
    }

    public void xuat() {
        super.xuat();
        System.out.printf("Mức lương: %,.2f\n", mucLuong);
    }

    public double tinhLuong() {
        return mucLuong;
    }

}
