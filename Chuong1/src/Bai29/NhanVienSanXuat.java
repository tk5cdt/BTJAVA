package Bai29;

import java.util.Scanner;

public class NhanVienSanXuat extends NhanVien{
    int soSanPham;

    public NhanVienSanXuat() {
        super();
        soSanPham = 0;
    }

    public NhanVienSanXuat(int soSanPham, String hoTen, int namVaoLam) {
        super(hoTen, namVaoLam);
        this.soSanPham = soSanPham;
    }

    public NhanVienSanXuat(int soSanPham) {
        this.soSanPham = soSanPham;
    }

    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        super.nhapThongTin();
        System.out.print("Số sản phẩm: ");
        soSanPham = sc.nextInt();
    }

    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Số sản phẩm: " + soSanPham);
    }

    public int tinhLuong() {
        return super.tinhLuong()+soSanPham*2000;
    }
}

