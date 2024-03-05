package Bai30;

import java.util.Scanner;
public class LiThuyet extends MonHoc{
    private float diemTL;
    public float getDiemTL() {
        return diemTL;
    }

    public void setDiemTL(float diemTL) {
        this.diemTL = diemTL;
    }
    private float diemGK;
    public float getDiemGK() {
        return diemGK;
    }

    public void setDiemGK(float diemGK) {
        this.diemGK = diemGK;
    }

    private float diemCK;

    public float getDiemCK() {
        return diemCK;
    }

    public void setDiemCK(float diemCK) {
        this.diemCK = diemCK;
    }
    public LiThuyet()
    {
        diemGK = 0;
        diemTL = 0;
        diemCK = 0;
    }

    public LiThuyet(String ma, String ten,int sotc, float tl, float gk, float ck)
    {
        super(ma, ten, sotc);
        diemTL = tl;
        diemGK = gk;
        diemCK = ck;
    }

    @Override
    public double tinhDiemTB() {
        return (diemTL * 0.2) + (diemGK * 0.3) + (diemCK * 0.5);
    }

    @Override
    public void nhapTT() {
        nhapMH();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập điểm tiểu luận: ");
        diemTL = scanner.nextFloat();
        System.out.println("Nhập điểm giữa kì: ");
        diemGK = scanner.nextFloat();
        System.out.println("Nhập điểm cuối kì: ");
        diemCK = scanner.nextFloat();
    }

    @Override
    public void xuatTT() {
        System.out.println("LÍ THUYẾT: " + getMaMH() + "--" + getTenMH() + "--" + getSoTC() + "--" + diemTL + "--" + diemGK + "--"+ diemCK + "--" + tinhDiemTB());
    }
}
