package Bai32;

import java.util.Scanner;

public class GVThinhGiang extends LDNgoaiBienChe {
    private String chuyenMon;
    private double soGioThinhGiang;

    public GVThinhGiang(String maSo, String hoTen, int namSinh, double mucLuong, String chuyenMon, double soGioThinhGiang) {
        super(maSo, hoTen, namSinh, mucLuong);
        this.chuyenMon = chuyenMon;
        this.soGioThinhGiang = soGioThinhGiang;
    }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập chuyên môn: ");
        chuyenMon = sc.nextLine();
        System.out.print("Nhập số giờ giảng: ");
        soGioThinhGiang = sc.nextDouble();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("Chuyên môn: %s\n", chuyenMon);
        System.out.printf("Số giờ giảng: %,.2f\n", soGioThinhGiang);
    }

    @Override
    public double tinhLuong() {
        double phuCapChuyenMon = 0;
        if (chuyenMon.equals("DH")) {
            phuCapChuyenMon = 1.0;
        } else if (chuyenMon.equals("ThS")) {
            phuCapChuyenMon = 1.2;
        } else if (chuyenMon.equals("TS")) {
        phuCapChuyenMon = 1.5;
        } else if (chuyenMon.equals("PGS")) {
            phuCapChuyenMon = 1.8;
        } else if (chuyenMon.equals("GS")) {
            phuCapChuyenMon = 2.0;
        }

        return super.tinhLuong() * soGioThinhGiang * phuCapChuyenMon;
    }
}

