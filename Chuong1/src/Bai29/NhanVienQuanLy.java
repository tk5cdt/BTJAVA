package Bai29;

import java.util.Scanner;

public class NhanVienQuanLy extends NhanVien{
    double heSoChucVu;
    int thuong;

    public NhanVienQuanLy(double heSoChucVu, int thuong, String hoTen, int namVaoLam) {
        super(hoTen, namVaoLam);
        this.heSoChucVu = heSoChucVu;
        this.thuong = thuong;
    }

    public NhanVienQuanLy(int heSoChucVu, int thuong) {
        this.heSoChucVu = heSoChucVu;
        this.thuong = thuong;
    }

    public NhanVienQuanLy() {
        super();
        this.heSoChucVu = 0;
        this.thuong = 0;
    }

    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        super.nhapThongTin();
        System.out.print("Hệ số chức vụ: ");
        heSoChucVu = Double.parseDouble(sc.nextLine());
        System.out.print("Thưởng: ");
        thuong = sc.nextInt();
    }

    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Hệ số chức vụ: " + heSoChucVu);
        System.out.println("Thưởng: " + thuong);
    }

    public int tinhLuong() {
        return (int)(super.tinhLuong()*heSoChucVu+thuong);
    }


}

