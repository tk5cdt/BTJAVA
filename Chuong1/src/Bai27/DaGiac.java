package Bai27;

import java.util.ArrayList;
import java.util.*;

public class DaGiac {
    private int soDinh;
    private List<Dinh> dsDinh;
    //mac dinh
    public DaGiac() {
        this.soDinh = 0;
        this.setDsDinh(new ArrayList<>());
    }
    //tham so
    public DaGiac(List<Dinh> dsDinh, int soDinh) {
        this.soDinh = soDinh;
        this.setDsDinh(dsDinh);
    }
    //copy
    public DaGiac(DaGiac dg) {
        this.setSoDinh(dg.getSoDinh());
        this.setDsDinh(dg.getDsDinh());
    }
    private void setDsDinh(List<Dinh> dsDinh) {
        this.dsDinh = dsDinh;
    }

    public int getSoDinh() {
        return soDinh;
    }

    public void setSoDinh(int soDinh) {
        this.soDinh = soDinh;
    }

    public List<Dinh> getDsDinh() {
        return dsDinh;
    }
    //nhap dinh
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap so dinh: ");
        this.setSoDinh(sc.nextInt());
        for (int i = 0; i < this.getSoDinh(); i++) {
            System.out.println("Nhap toa do dinh thu " + (i + 1) + ": ");
            Dinh d = new Dinh();
            d.nhap();
            this.getDsDinh().add(d);
        }
    }
    //xuat dinh
    public void xuat() {
        for (int i = 0; i < this.getSoDinh(); i++) {
            System.out.println("Toa do dinh thu " + (i + 1) + ": ");
            this.getDsDinh().get(i).xuat();
        }
    }
    //tinh chu vi
    public double tinhChuVi() {
        double chuVi = 0;
        for (int i = 0; i < this.getSoDinh(); i++) {
            chuVi += this.getDsDinh().get(i).tinhDoDai(this.getDsDinh().get((i + 1) % this.getSoDinh()));
        }
        return chuVi;
    }
    //tim diem xa goc toa do nhat
    public Dinh timDiemXaNhat() {
        Dinh xa = this.getDsDinh().getFirst();
        for (int i = 1; i < this.getSoDinh(); i++) {
            if (this.getDsDinh().get(i).tinhKhoangCachToiGocToaDo() > xa.tinhKhoangCachToiGocToaDo()) {
                xa = this.getDsDinh().get(i);
            }
        }
        return xa;
    }
}
