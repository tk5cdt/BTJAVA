package Bai31;

import java.util.Scanner;

public abstract class CBatDongSan {
    String maSo;
    double chieuDai;
    double chieuRong;

    public String getMaSo(){
        return maSo;
    }
    public void setMaSo(String value){
        if(value.length()>0)
            maSo=value;
    }

    public double getChieuDai(){
        return chieuDai;
    }
    public void setChieuDai(double value){
        if(value >0)
            chieuDai=value;
    }

    public double getChieuRong(){
        return chieuRong;
    }
    public void setChieuRong(double value){
        if(value>0)
            chieuRong=value;
    }

    public CBatDongSan(){
        maSo="CTT000";
        chieuDai=0;
        chieuRong=0;
    }

    public CBatDongSan(String maSo, double chieuDai, double chieuRong){
        this.maSo = maSo;
        this.chieuDai=chieuDai;
        this.chieuRong = chieuRong;
    }

    abstract public double giaBatDongSan();

    abstract public void nhapTT();

    public void nhap() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap ma so: ");
        maSo = scanner.nextLine();
        System.out.print("Nhap chieu dai: ");
        chieuDai = Double.parseDouble(new Scanner(System.in).nextLine());
        System.out.print("Nhap chieu rong: ");
        chieuRong = Double.parseDouble(new Scanner(System.in).nextLine());
    }

    @Override
    public String toString() {
        return maSo + "___" + chieuDai + " ___" + chieuRong;
    }

    public void xuat() {
        System.out.printf("Ma so: %s \t Chieu dai: %.2f \t Chieu rong: %.2f%n", maSo, chieuDai, chieuRong);
    }
}
