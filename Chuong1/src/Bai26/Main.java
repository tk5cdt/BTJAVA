package Bai26;

public class Main {
    public static void main(String[] args) {
        TamGiac tg = new TamGiac();

        tg.Nhap();
        tg.Xuat();
        System.out.println("Chu vi hình tam giác là: " + tg.tinhChuVi());
        System.out.println("Diện tích hình tam giác là: " + tg.tinhDienTich());
    }
}
