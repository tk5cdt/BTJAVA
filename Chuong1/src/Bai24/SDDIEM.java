package Bai24;

public class SDDIEM {
    public static void main(String[] agrs)
    {
        DIEM a = new DIEM();
        a.setX(3);
        a.setY(4);
        a.HienThi();

        DIEM b = new DIEM();
        b.nhapDiem();
        b.HienThi();

        DIEM c = new DIEM();
        c.setX(b.GiaTriX()*-1);
        c.setY(b.GiaTriY()*-1);
        c.HienThi();

        System.out.println(b.KhoangCach());
        System.out.println(a.KhoangCach(b));
    }
}
