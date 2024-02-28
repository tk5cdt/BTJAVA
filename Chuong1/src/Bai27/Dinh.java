package Bai27;

import java.util.Scanner;

public class Dinh {
    private int x;
    private int y;
    public Dinh()
    {
        this.setX(0);
        this.setY(0);
    }
    public Dinh(int x, int y) {
        this.setX(x);
        this.setY(y);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhap x: ");
        this.setX(sc.nextInt());
        System.out.println("Nhap y: ");
        this.setY(sc.nextInt());
    }
    public void xuat() {
        System.out.println("x: " + this.getX() + ", y: " + this.getY());
    }

    public double tinhDoDai(Dinh dinh) {
        return Math.sqrt(Math.pow(this.getX() - dinh.getX(), 2) + Math.pow(this.getY() - dinh.getY(), 2));
    }

    public double tinhKhoangCachToiGocToaDo() {
        return Math.sqrt(Math.pow(this.getX(), 2) + Math.pow(this.getY(), 2));
    }
}
