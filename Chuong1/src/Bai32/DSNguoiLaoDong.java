package Bai32;

import java.util.ArrayList;

public class DSNguoiLaoDong {
    private ArrayList<NguoiLaoDong> dsNLD;

    public DSNguoiLaoDong() {
        dsNLD = new ArrayList<>();
    }

    public void them(NguoiLaoDong nld) {
        dsNLD.add(nld);
    }

    public void xuat() {
        for (NguoiLaoDong nld : dsNLD) {
            nld.xuat();
        }
    }

    public double tinhTongLuong() {
        double tongLuong = 0;
        for (NguoiLaoDong nld : dsNLD) {
            tongLuong += nld.tinhLuong();
        }
        return tongLuong;
    }

    public int timKiemTheoMaSo(String maSo) {
        for (int i = 0; i < dsNLD.size(); i++) {
            if (dsNLD.get(i).getMaSo().equals(maSo)) {
                return i;
            }
        }
        return -1;
    }

    public ArrayList<NguoiLaoDong> getDsNLD() {
        return dsNLD;
    }

    public void setDsNLD(ArrayList<NguoiLaoDong> dsNLD) {
        this.dsNLD = dsNLD;
    }
}