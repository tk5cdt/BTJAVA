package Bai31;

public class CBietThu extends CBatDongSan implements IPhiKinhDoanh{
    private String ma;
    private double dai;
    private double rong;

    public CBietThu(){
        super();
    }
    public CBietThu(String ma, double dai, double rong)
    {
        super(ma, dai, rong);
    }

    @Override
    public double giaBatDongSan() {
        return chieuDai*chieuRong*400000;
    }

    @Override
    public void nhapTT() {
        super.nhap();
    }

    @Override
    public double tinhPhiKD() {
        return chieuDai*chieuRong*1000;
    }

    @Override
    public String toString(){
        return super.toString()+"("+ giaBatDongSan() + ")___ " + tinhPhiKD();
    }
}
