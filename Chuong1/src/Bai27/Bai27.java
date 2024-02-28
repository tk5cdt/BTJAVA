package Bai27;

public class Bai27 {
    public static void main(String[] args) {
        DaGiac dg = new DaGiac();
        dg.nhap();
        dg.xuat();
        System.out.println("Chu vi: " + dg.tinhChuVi());
        System.out.println("Dinh xa nhat: ");
        dg.timDiemXaNhat().xuat();
    }
}
