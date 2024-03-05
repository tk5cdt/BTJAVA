package Bai32;

import java.util.Scanner;

public abstract class NguoiLaoDong {
    String maSo, hoTen;
    int namSinh;
    public NguoiLaoDong(String maSo, String hoTen, int namSinh) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    public void xuat() {
        System.out.printf("Mã số: %s\n", maSo);
        System.out.printf("Họ tên: %s\n", hoTen);
        System.out.printf("Năm sinh: %d\n", namSinh);
    }

    public abstract double tinhLuong();

    public String getMaSo() {
        return maSo;
    }

    public void setMaSo(String maSo) {
        this.maSo = maSo;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public int getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(int namSinh) {
        this.namSinh = namSinh;
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập mã số: ");
        maSo = sc.nextLine();
        System.out.print("Nhập họ tên: ");
        hoTen = sc.nextLine();
        System.out.print("Nhập năm sinh: ");
        namSinh = sc.nextInt();
    }
}