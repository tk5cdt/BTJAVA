package Bai29;

import java.util.Scanner;

public class Bai29 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        CongTy ct = new CongTy();
        ct.nhapThongTin();

        ct.xuatThongTin();

        System.out.println("-=- Thông tin lương công ty -=-");
        System.out.println("Tổng lương của nhân viên văn phòng: " + ct.tinhLuongNhanVienVanPhong());
        System.out.println("Tổng lương của nhân viên sản xuất: " + ct.tinhLuongNhanVienSanXuat());
        System.out.println("Tổng lương của nhân viên quản lý: " + ct.tinhLuongNhanVienQuanLy());
        System.out.println("Tổng lương công ty: "+ ct.tinhLuongCongTy());
    }
}
