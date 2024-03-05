package Bai31;

public class CDatTrong extends CBatDongSan {
    private String ma;
    private double dai;
    private double rong;

    public CDatTrong() {
        super();
    }

    public CDatTrong(String ma, double dai, double rong) {
        super(ma, dai, rong);
    }

    public double giaBatDongSan() {
        return chieuDai * chieuRong * 10000;
    }

    public void nhapTT() {
        super.nhap();
    }

    @Override
    public String toString() {
        return super.toString() + "(" + giaBatDongSan() + ")";
    }
}
