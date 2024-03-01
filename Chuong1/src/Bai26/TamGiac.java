package Bai26;
import java.util.*;
public class TamGiac {
    private double x1, y1; // Tọa độ đỉnh 1
    private double x2, y2; // Tọa độ đỉnh 2
    private double x3, y3; // Tọa độ đỉnh 3
    Scanner in = new Scanner(System.in);

    // Khởi tạo mặc định
    public TamGiac() {
        this.x1 = 0;
        this.y1 = 0;
        this.x2 = 0;
        this.y2 = 0;
        this.x3 = 0;
        this.y3 = 0;
    }
    // Khởi tạo có tham số
    public TamGiac(double x1, double y1, double x2, double y2, double x3, double y3) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
        this.x3 = x3;
        this.y3 = y3;
    }
    // Khởi tạo sao chép
    public TamGiac(TamGiac other) {
        this.x1 = other.x1;
        this.y1 = other.y1;
        this.x2 = other.x2;
        this.y2 = other.y2;
        this.x3 = other.x3;
        this.y3 = other.y3;
    }
    public double getX1() {
        return x1;
    }
    public void setX1(double x1) {
        this.x1 = x1;
    }

    public double getY1() {
        return y1;
    }
    public void setY1(double y1) {
        this.y1 = y1;
    }

    public double getX2() {
        return x2;
    }

    public double getY2() {
        return y2;
    }

    public void setX2(double x2) {
        this.x2 = x2;
    }

    public void setY2(double y2) {
        this.y2 = y2;
    }

    public void setX3(double x3) {
        this.x3 = x3;
    }

    public void setY3(double y3) {
        this.y3 = y3;
    }

    public double getX3() {
        return x3;
    }

    public double getY3() {
        return y3;
    }

    public void Nhap()
    {
        System.out.println("Nhập vào tọa độ đỉnh 1: ");
        System.out.print("Nhập vào tọa độ x1: ");
        x1 = in.nextInt();
        System.out.print("Nhập vào tọa độ y1: ");
        y1 = in.nextInt();
        System.out.println("Nhập vào tọa độ đỉnh 2: ");
        System.out.print("Nhập vào tọa độ x2: ");
        x2 = in.nextInt();
        System.out.print("Nhập vào tọa độ y2: ");
        y2 = in.nextInt();
        System.out.println("Nhập vào tọa độ đỉnh 3: ");
        System.out.print("Nhập vào tọa độ x3: ");
        x3 = in.nextInt();
        System.out.print("Nhập vào tọa độ y3: ");
        y3 = in.nextInt();
    }
    public void Xuat() {
        System.out.print("Tọa độ các đỉnh sau khi nhập là: ");
        System.out.println("Đỉnh 1: " + x1 + " - " + y1 + "\n" + "Đỉnh 2: " + x2 + " - "  + y2 + "\n" + "Đỉnh 3: " + x3 + " - " + y3);
    }
    public double tinhChuVi() {
        double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double c = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        return a + b + c;
    }

    public double tinhDienTich() {
        double p = tinhChuVi() / 2;
        double a = Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
        double b = Math.sqrt(Math.pow(x3 - x2, 2) + Math.pow(y3 - y2, 2));
        double c = Math.sqrt(Math.pow(x1 - x3, 2) + Math.pow(y1 - y3, 2));
        return Math.sqrt(p * (p - a) * (p - b) * (p - c));
    }


}
