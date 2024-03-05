package Bai25;
import java.util.Scanner;


public class PhanSo {
    private int tuSo;
    private int mauSo;

    //Hàm khởi tạo mặc định
    public PhanSo() {
        this.tuSo = 0;
        this.mauSo = 1;
    }

    //Hàm khởi tạo 2 tham số
    public PhanSo(int tuSo, int mauSo) {
        this.tuSo = tuSo;
        if (mauSo != 0) {
            this.mauSo = mauSo;
        } else {
            System.out.println("Lỗi: Mẫu số không thể bằng 0.");
            this.mauSo = 1;
        }
        rutGonPhanSo();
    }

    private void rutGonPhanSo() {
        int ucln = timUCLN(Math.abs(this.tuSo), Math.abs(this.mauSo));
        this.tuSo /= ucln;
        this.mauSo /= ucln;
    }

    private int timUCLN(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return Math.abs(a);
    }

    //Hàm nhập phân số
    public void nhapPhanSo() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhập tử số: ");
        int tuSo = scanner.nextInt();
        System.out.print("Nhập mẫu số: ");
        int mauSo = scanner.nextInt();

        while (mauSo == 0) {
            System.out.println("Lỗi: Mẫu số không thể bằng 0. Nhập lại.");
            System.out.print("Nhập mẫu số: ");
            mauSo = scanner.nextInt();
        }
        this.tuSo = tuSo;
        this.mauSo = mauSo;
        rutGonPhanSo();
    }

    //Hàm hiển thị phân số
    public void hienThiPhanSo() {
        if (tuSo == 0) {
            System.out.println("0");
        } else if (mauSo == 1) {
            System.out.println(tuSo);
        } else {
            System.out.println(tuSo + "/" + mauSo);
        }
    }

    //Hàm nghịch đảo phân số
    public void nghichDao() {
        if (tuSo != 0) {
            int temp = tuSo;
            tuSo = mauSo;
            mauSo = temp;
        } else {
            System.out.println("Lỗi: Không thể nghịch đảo với tử số bằng 0.");
        }
    }

    //hàm tính giá trị nghịch đảo của phân số
    public PhanSo giaTriNghichDao() {
        if (tuSo != 0) {
            return new PhanSo(mauSo, tuSo);
        } else {
            System.out.println("Lỗi: Không thể nghịch đảo với tử số bằng 0.");
            return null;
        }
    }

    //Hàm tính giá trị thực của phân số
    public double giaTriThuc() {
        return (double) tuSo / mauSo;
    }

    //Hàm so sánh lớn hơn với phân số a
    public boolean lonHon(PhanSo a) {
        // So sánh giá trị thực của phân số
        return giaTriThuc() > a.giaTriThuc();
    }

    //Các hàm cộng trừ nhân chia phân số với một phân số
    public PhanSo cong(PhanSo a) {
        int newTuSo = this.tuSo * a.mauSo + a.tuSo * this.mauSo;
        int newMauSo = this.mauSo * a.mauSo;
        return new PhanSo(newTuSo, newMauSo);
    }

    public PhanSo tru(PhanSo a) {
        int newTuSo = this.tuSo * a.mauSo - a.tuSo * this.mauSo;
        int newMauSo = this.mauSo * a.mauSo;
        return new PhanSo(newTuSo, newMauSo);
    }

    public PhanSo nhan(PhanSo a) {
        int newTuSo = this.tuSo * a.tuSo;
        int newMauSo = this.mauSo * a.mauSo;
        return new PhanSo(newTuSo, newMauSo);
    }

    public PhanSo chia(PhanSo a) {
        if (a.tuSo != 0) {
            int newTuSo = this.tuSo * a.mauSo;
            int newMauSo = this.mauSo * a.tuSo;
            return new PhanSo(newTuSo, newMauSo);
        } else {
            System.out.println("Lỗi: Không thể chia cho phân số có tử số bằng 0.");
            return null;
        }
    }

    //Các hàm cộng trừ nhân chia phân số với một số nguyên
    public PhanSo cong(int soNguyen) {
        return cong(new PhanSo(soNguyen, 1));
    }

    public PhanSo tru(int soNguyen) {
        return tru(new PhanSo(soNguyen, 1));
    }

    public PhanSo nhan(int soNguyen) {
        return nhan(new PhanSo(soNguyen, 1));
    }

    public PhanSo chia(int soNguyen) {
        return chia(new PhanSo(soNguyen, 1));
    }

    public static void main(String[] args) {
        // Test lớp PhanSo
        PhanSo phanSo = new PhanSo();
        phanSo.nhapPhanSo();

        System.out.print("Phan so vua nhap: ");
        phanSo.hienThiPhanSo();

        phanSo.nghichDao();
        System.out.print("Nghịch đảo phân số: ");
        phanSo.hienThiPhanSo();

        PhanSo giaTriNghichDao = phanSo.giaTriNghichDao();
        System.out.print("Giá trị nghịch đảo: ");
        if (giaTriNghichDao != null) {
            giaTriNghichDao.hienThiPhanSo();
        }

        System.out.println("Giá trị thực của phân số: " + phanSo.giaTriThuc());

        PhanSo phanSo2 = new PhanSo(2, 3);
        System.out.print("Phan so 2: ");
        phanSo2.hienThiPhanSo();

        System.out.print("Phan so 1 có giá trị lớn hơn Phan so 2: " + phanSo.lonHon(phanSo2));

        System.out.print("Phan so 1 + Phan so 2: ");
        PhanSo tong = phanSo.cong(phanSo2);
        tong.hienThiPhanSo();

        System.out.print("Phan so 1 - Phan so 2: ");
        PhanSo hieu = phanSo.tru(phanSo2);
        hieu.hienThiPhanSo();

        System.out.print("Phan so 1 * Phan so 2: ");
        PhanSo tich = phanSo.nhan(phanSo2);
        tich.hienThiPhanSo();

        System.out.print("Phan so 1 / Phan so 2: ");
        PhanSo thuong = phanSo.chia(phanSo2);
        thuong.hienThiPhanSo();

        System.out.print("Phan so 1 + 2: ");
        PhanSo tong2 = phanSo.cong(2);
        tong2.hienThiPhanSo();

        System.out.print("Phan so 1 - 2: ");
        PhanSo hieu2 = phanSo.tru(2);
        hieu2.hienThiPhanSo();

        System.out.print("Phan so 1 * 2: ");
        PhanSo tich2 = phanSo.nhan(2);
        tich2.hienThiPhanSo();

        System.out.print("Phan so 1 / 2: ");
        PhanSo thuong2 = phanSo.chia(2);
        thuong2.hienThiPhanSo();
    }
}
