package Bai29;

import java.util.Scanner;

public class NhanVienVanPhong extends NhanVien {

    int soNgayLamViec;
    int troCap;

    public NhanVienVanPhong(int soNgayLamViec, int troCap, String hoTen, int namVaoLam) {
        super(hoTen, namVaoLam);
        this.soNgayLamViec = soNgayLamViec;
        this.troCap = troCap;
    }

    public NhanVienVanPhong(int soNgayLamViec, int troCap) {
        this.soNgayLamViec = soNgayLamViec;
        this.troCap = troCap;
    }

    public NhanVienVanPhong() {
        super();
        this.soNgayLamViec = 0;
        this.troCap = 0;
    }



    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        super.nhapThongTin();
        System.out.print("Số ngày làm việc: ");
        soNgayLamViec = sc.nextInt();
        System.out.print("Trợ cấp: ");
        troCap = sc.nextInt();
    }

    public void xuatThongTin() {
        super.xuatThongTin();
        System.out.println("Số ngày làm việc: " + soNgayLamViec);
        System.out.println("Trợ cấp: " + troCap);
    }

    public int tinhLuong() {
        return super.tinhLuong()+soNgayLamViec*100000+troCap;
    }

}
