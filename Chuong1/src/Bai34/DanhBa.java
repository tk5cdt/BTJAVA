package Bai34;

import java.io.Serializable;

public class DanhBa implements Serializable {
    private String ten;
    private String soDienThoai;

    public DanhBa(String ten, String soDienThoai) {
        this.ten = ten;
        this.soDienThoai = soDienThoai;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Override
    public String toString() {
        return "Tên: " + ten + "\nSố điện thoại: " + soDienThoai;
    }
}
