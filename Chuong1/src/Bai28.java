import java.util.Scanner;

class Diem {
    int x, y;

    public Diem(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void hienThiThongTin() {
        System.out.println("Toa do: (" + x + ", " + y + ")");
    }

    public void doiDiem(int deltaX, int deltaY) {
        x += deltaX;
        y += deltaY;
    }
}

class DiemMau extends Diem {
    String mau;

    public DiemMau(int x, int y, String mau) {
        super(x, y);
        this.mau = mau;
    }

    public void ganMau(String mauMoi) {
        mau = mauMoi;
    }

    @Override
    public void hienThiThongTin() {
        super.hienThiThongTin();
        System.out.println("Mau: " + mau);
    }
}

public class Bai28 {
    public static void main(String[] args) {
        // Tạo điểm màu A có tọa độ là (5, 10) và màu là trắng
        DiemMau A = new DiemMau(5, 10, "Trang");
        System.out.println("Thong tin diem A:");
        A.hienThiThongTin();

        // Tạo điểm màu B và nhập giá trị từ bàn phím
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap toa do x cho diem B: ");
        int xB = scanner.nextInt();
        System.out.print("Nhap toa do y cho diem B: ");
        int yB = scanner.nextInt();
        System.out.print("Nhap mau cho diem B: ");
        String mauB = scanner.next();

        DiemMau B = new DiemMau(xB, yB, mauB);



        // Hiển thị tọa độ điểm B ra màn hình
        System.out.println("\n Toa do diem B : ");
        B.hienThiThongTin();

        // Dời điểm B đi (10, 8)
        B.doiDiem(10, 8);
        // Gán màu mới cho điểm B là "Vàng"
        B.ganMau("Vang");

        // Hiển thị thông tin điểm B sau khi gán màu mới
        System.out.println("\n Thong tin diem B sau khi doi:");
        B.hienThiThongTin();
    }
}
