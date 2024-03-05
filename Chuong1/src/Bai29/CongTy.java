package Bai29;

import java.util.Scanner;

public class CongTy {

    NhanVienVanPhong dsNVVP[];
    NhanVienSanXuat dsNVSX[];
    NhanVienQuanLy dsNVQL[];
    int soNVVP;
    int soNVSX;
    int soNVQL;

    public CongTy() {
        dsNVVP = new NhanVienVanPhong[0];
        dsNVSX = new NhanVienSanXuat[0];
        dsNVQL = new NhanVienQuanLy[0];
        soNVQL = 0;
        soNVSX = 0;
        soNVQL = 0;

    }

    public CongTy(NhanVienVanPhong[] dsNVVP, NhanVienSanXuat[] dsNVSX, NhanVienQuanLy[] dsNVQL, int soNVVP, int soNVSX, int soNVQL) {
        this.dsNVVP = dsNVVP;
        this.dsNVSX = dsNVSX;
        this.dsNVQL = dsNVQL;
        this.soNVVP = soNVVP;
        this.soNVSX = soNVSX;
        this.soNVQL = soNVQL;
    }

    public void nhapThongTin() {
        Scanner sc = new Scanner(System.in);
        // NVVP
        System.out.print("Nhập số lượng nhân viên văn phòng: ");
        soNVVP = sc.nextInt();
        dsNVVP = new NhanVienVanPhong[soNVVP];
        for (int i = 0; i < soNVVP; i++) {
            System.out.println("Nhập thông tin nhân viên văn phòng thứ " + (i + 1));
            dsNVVP[i] = new NhanVienVanPhong();
            dsNVVP[i].nhapThongTin();
        }
        //NVSX
        System.out.print("Nhập số lượng nhân viên sản xuất: ");
        soNVSX = sc.nextInt();
        dsNVSX = new NhanVienSanXuat[soNVSX];
        for (int i = 0; i < soNVSX; i++) {
            System.out.println("Nhập thông tin nhân viê9n sản xuất thứ " + (i + 1));
            dsNVSX[i] = new NhanVienSanXuat();
            dsNVSX[i].nhapThongTin();
        }
        //NVQL
        System.out.print("Nhập số lượng nhân viên quản lý: ");
        soNVQL = sc.nextInt();
        dsNVQL = new NhanVienQuanLy[soNVQL];
        for (int i = 0; i < soNVQL; i++) {
            System.out.println("Nhập thông tin nhân viên quản lý thứ " + (i + 1));
            dsNVQL[i] = new NhanVienQuanLy();
            dsNVQL[i].nhapThongTin();
        }
    }

    public void xuatThongTin() {
        System.out.println(" ==== Nhân viên văn phòng ====");
        for (int i = 0; i < soNVVP; i++) {
            System.out.println("\tNhân viên thứ " + (i + 1));
            dsNVVP[i].xuatThongTin();
            System.out.println("Lương: " + dsNVVP[i].tinhLuong());
        }

        System.out.println(" ==== Nhân viên sản xuất ====");
        for (int i = 0; i < soNVSX; i++) {
            System.out.println("\tNhân viên thứ " + (i + 1));
            dsNVSX[i].xuatThongTin();
            System.out.println("Lương: " + dsNVSX[i].tinhLuong());
        }

        System.out.println(" ==== Nhân viên quản lý ====");
        for (int i = 0; i < soNVQL; i++) {
            System.out.println("\tNhân viên thứ " + (i + 1));
            dsNVQL[i].xuatThongTin();
            System.out.println("Lương: " + dsNVQL[i].tinhLuong());
        }

    }

    public int tinhLuongNhanVienVanPhong() {
        int sum = 0;
        for (NhanVienVanPhong vp : dsNVVP) {
            sum += vp.tinhLuong();
        }
        return sum;
    }

    public int tinhLuongNhanVienSanXuat() {
        int sum = 0;
        for (NhanVienSanXuat vp : dsNVSX) {
            sum += vp.tinhLuong();
        }
        return sum;
    }

    public int tinhLuongNhanVienQuanLy() {
        int sum = 0;
        for (NhanVienQuanLy vp : dsNVQL) {
            sum += vp.tinhLuong();
        }
        return sum;
    }

    public int tinhLuongCongTy() {
        return tinhLuongNhanVienQuanLy() + tinhLuongNhanVienSanXuat() + tinhLuongNhanVienVanPhong();
    }
}

