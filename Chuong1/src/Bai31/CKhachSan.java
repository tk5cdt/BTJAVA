package Bai31;

public class CKhachSan extends CBatDongSan implements IPhiKinhDoanh {
    private double soSao;

    public CKhachSan() {
        soSao = 0;
    }

    public CKhachSan(String maSo, double chieuDai, double chieuRong, int soSao) {
        super(maSo, chieuDai, chieuRong);
        this.soSao = soSao;
    }

    @Override
    public double giaBatDongSan() {
        return chieuRong * chieuDai * 100000 + soSao * 50000;
    }

    public double tinhPhiKD() {
        return chieuRong * 5000;
    }

    @Override
    public void nhapTT() {
        super.nhap();
        System.out.println("Nhap so sao: ");
        soSao = Double.parseDouble(System.console().readLine());
    }

    @Override
    public String toString() {
        return super.toString() + "___" + soSao + "(" + giaBatDongSan() + ")__" + tinhPhiKD();
    }
}
