package Bai30;

import java.util.Scanner;

public abstract class MonHoc {
    private String maMH;

    public String getMaMH() {
        return maMH;
    }

    public void setMaMH(String maMH) {
        this.maMH = maMH;
    }

    private String tenMH;

    public String getTenMH() {
        return tenMH;
    }

    public void setTenMH(String tenMH) {
        this.tenMH = tenMH;
    }

    private int soTC;

    public int getSoTC() {
        return soTC;
    }

    public void setSoTC(int soTC) {
        this.soTC = soTC;
    }

    public MonHoc()
    {
        maMH = "MH001";
        tenMH = "";
        soTC = 0;
    }

    public MonHoc(String ma, String ten, int soTC)
    {
        maMH = ma;
        tenMH = ten;
        this.soTC = soTC;
    }

    public abstract double tinhDiemTB();
    public abstract void xuatTT();
    public abstract void nhapTT();
    public void nhapMH()
    {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã môn học: ");
        maMH = sc.nextLine();
        System.out.print("Nhập tên môn học: ");
        tenMH = sc.nextLine();
        System.out.print("Nhập số tín chỉ: ");
        soTC = Integer.parseInt(new Scanner(System.in).nextLine());
    }
}
