package Bai30;

import java.util.Scanner;

public class ThucHanh extends MonHoc{
    private float diemKT1;

    public float getDiemKT1() {
        return diemKT1;
    }

    public void setDiemKT1(float diemKT1) {
        this.diemKT1 = diemKT1;
    }

    private float diemKT2;

    public float getDiemKT2() {
        return diemKT2;
    }

    public void setDiemKT2(float diemKT2) {
        this.diemKT2 = diemKT2;
    }

    private float diemKT3;

    public float getDiemKT3() {
        return diemKT3;
    }

    public void setDiemKT3(float diemKT3) {
        this.diemKT3 = diemKT3;
    }

    private float diemKT4;

    public float getDiemKT4() {
        return diemKT4;
    }

    public void setDiemKT4(float diemKT4) {
        this.diemKT4 = diemKT4;
    }

    public ThucHanh()
    {
        diemKT1 = 0;
        diemKT2 = 0;
        diemKT3 = 0;
        diemKT4 = 0;
    }

    public ThucHanh(String ma, String ten, int sotc, float d1, float d2, float d3, float d4)
    {
        super(ma, ten, sotc);
        diemKT1 = d1;
        diemKT2 = d2;
        diemKT3 = d3;
        diemKT4 = d4;
    }

    @Override
    public double tinhDiemTB() {
        return (diemKT1 + diemKT2 + diemKT3 + diemKT4) / 4;
    }

    @Override
    public void nhapTT() {
        nhapMH();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập điểm kiểm tra lần 1: ");
        diemKT1 = scanner.nextFloat();
        System.out.println("Nhập điểm kiểm tra lần 2: ");
        diemKT2 = scanner.nextFloat();
        System.out.println("Nhập điểm kiểm tra lần 3: ");
        diemKT3 = scanner.nextFloat();
        System.out.println("Nhập điểm kiểm tra lần 4: ");
        diemKT4 = scanner.nextFloat();
    }

    @Override
    public void xuatTT() {
        System.out.println("THỰC HÀNH: " + getMaMH() + "--" + getTenMH() + "--" + getSoTC() + "--" + diemKT1 + "--" + diemKT2 + "--"+ diemKT3 + "--" + diemKT4 + "--" + tinhDiemTB());
    }
}
