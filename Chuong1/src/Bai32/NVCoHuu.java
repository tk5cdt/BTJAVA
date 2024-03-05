package Bai32;

import java.util.Scanner;

public class NVCoHuu extends LDBienChe implements IXetKhenThuong {
    private int soGioLamThem;

    public NVCoHuu(String maSo, String hoTen, int namSinh, double HSL, double LCS, double tinhKhenThuong, int soGioLamThem) {
        super(maSo, hoTen, namSinh, HSL, LCS);
        this.soGioLamThem = soGioLamThem;
    }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);

        System.out.print("Số giờ làm thêm: ");
        soGioLamThem = sc.nextInt();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("Số gi làm thêm: %d\n", soGioLamThem);
    }

    @Override
    public double tinhLuong() {
        return super.tinhLuong() * soGioLamThem * 50000;
    }

    @Override
    public double tinhKhenThuong(int soGioLamThem) {
        if(soGioLamThem > 40)
            return 1000000;
        return 0;
    }
}
