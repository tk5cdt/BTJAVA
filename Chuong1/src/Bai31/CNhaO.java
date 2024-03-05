package Bai31;

public class CNhaO extends CBatDongSan {
    private int soLau;

    public CNhaO() {
        soLau = 1;
    }

    public CNhaO(String maSo, double chieuDai, double chieuRong, int soLau) {
        super(maSo, chieuDai, chieuRong);
        this.soLau = soLau;
    }

    @Override
    public double giaBatDongSan() {
        return chieuRong * chieuDai * 10000 + soLau * 100000;
    }

    @Override
    public void nhapTT() {
        super.nhap();
        System.out.println("Nhap so lau: ");
        soLau = Integer.parseInt(System.console().readLine());
    }

    @Override
    public String toString() {
        return super.toString() + "___" + soLau + "(" + giaBatDongSan() + ")";
    }
}
