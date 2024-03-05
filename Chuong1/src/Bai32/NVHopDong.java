package Bai32;

import java.util.Scanner;

public class NVHopDong extends LDNgoaiBienChe implements IXetKhenThuong {
    private int ngayCong;

    public NVHopDong(String maSo, String hoTen, int namSinh, double mucLuong, int ngayCong) {
        super(maSo, hoTen, namSinh, mucLuong);
        this.ngayCong = ngayCong;
    }

    @Override
    public void nhap() {
        super.nhap();
        Scanner sc = new Scanner(System.in);

        System.out.print("Nhập ngày công: ");
        ngayCong = sc.nextInt();
    }

    @Override
    public void xuat() {
        super.xuat();
        System.out.printf("Số giờ giảng dạy: %d\n", ngayCong);
    }

    @Override
    public double tinhLuong() {
        return super.tinhLuong() * ngayCong;
    }

    @Override
    public double tinhKhenThuong(int ngayCong) {
        if (ngayCong > 25)
            return 1500000;
        return 0;
    }
}
