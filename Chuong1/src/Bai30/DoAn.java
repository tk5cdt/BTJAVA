package Bai30;

import java.util.Scanner;
public class DoAn extends MonHoc {
    private float diemGVHD;

    public float getDiemGVHD() {
        return diemGVHD;
    }

    public void setDiemGVHD(float diemGVHD) {
        this.diemGVHD = diemGVHD;
    }

    private float diemGVPB;

    public float getDiemGVPB() {
        return diemGVPB;
    }

    public void setDiemGVPB(float diemGVPB) {
        this.diemGVPB = diemGVPB;
    }

    public DoAn()
    {
        diemGVHD = 0;
        diemGVPB = 0;
    }

    public DoAn(String ma, String ten, int sotc, float hd, float pb)
    {
        super(ma, ten, sotc);
        diemGVHD = hd;
        diemGVPB = pb;
    }

    @Override
    public double tinhDiemTB() {
        return (diemGVHD + diemGVPB) / 2;
    }

    @Override
    public void nhapTT() {
        nhapMH();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập điểm hướng dẫn: ");
        diemGVHD = scanner.nextFloat();
        System.out.println("Nhập điểm phản biện: ");
        diemGVPB = scanner.nextFloat();
    }

    @Override
    public void xuatTT() {
        System.out.println("ĐỒ ÁN: " + getMaMH() + "--" + getTenMH() + "--" + getSoTC() + "--" + diemGVHD + "--" + diemGVPB + "--" + tinhDiemTB());
    }

}
