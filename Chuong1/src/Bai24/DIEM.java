package Bai24;

import java.util.Scanner;

public class DIEM {
    private int x;
    private int y;

    public void Diem() {
        x = 0;
        y = 0;
    }

    public void Diem(int x, int y)
    {
        this.x=x;
        this.y=y;
    }

    public  void nhapDiem()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhập toạ độ x:");
        x = scanner.nextInt();
        System.out.println("Nhập toạ độ y:");
        y = scanner.nextInt();
    }

    public void HienThi()
    {
        System.out.println(x+","+y);
    }

    public void DoiDiem(int dx, int dy)
    {
        x += dx;
        y += dy;
    }

    public int GiaTriX()
    {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int GiaTriY()
    {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public float KhoangCach()
    {
        return (float)(Math.sqrt(Math.pow(x,2)+Math.pow(y,2)));
    }

    public float KhoangCach(DIEM d)
    {
        return (float) Math.sqrt(Math.pow(this.x - d.GiaTriX(),2)+Math.pow(this.y - d.GiaTriY(),2));
    }
}
