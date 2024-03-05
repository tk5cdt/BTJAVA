package Bai25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.List;
public class SDPhanSo {
    public static void main(String[] args)
    {
        PhanSo a = new PhanSo(3, 7);
        PhanSo b = new PhanSo(4, 9);
        System.out.println("Phân số a: ");
        a.hienThiPhanSo();
        System.out.println("Phân số b: ");
        b.hienThiPhanSo();

        Scanner scanner = new Scanner(System.in);
        PhanSo x = new PhanSo();
        PhanSo y = new PhanSo();
        System.out.println("Nhập phân số x: ");
        x.nhapPhanSo();
        System.out.println("Nhập phân số y: ");
        y.nhapPhanSo();

        System.out.println("Nghịch đạo của phân số x: ");
        PhanSo xNghichDao = x.giaTriNghichDao();
        xNghichDao.hienThiPhanSo();

        PhanSo tong = x.cong(y);
        System.out.println("Tổng của phân số x và y: ");
        tong.hienThiPhanSo();

        List<PhanSo> dsPS = new ArrayList<>();
        System.out.println("Nhập số lượng phân số: ");
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            PhanSo phanSo = new PhanSo();
            System.out.println("Nhập phân số thứ" + (i + 1) + ": ");
            phanSo.nhapPhanSo();
            dsPS.add(phanSo);
        }

        System.out.println("Danh sách phân số: ");
        for (PhanSo z : dsPS) {
            z.hienThiPhanSo();
        }

        PhanSo tongPhanSo = new PhanSo();
        for (PhanSo phanSo : dsPS) {
            tongPhanSo = tongPhanSo.cong(phanSo);
        }
        System.out.println("Tổng của " + n + " phân số: ");
        tongPhanSo.hienThiPhanSo();

        PhanSo maxPhanSo = Collections.max(dsPS, (a1, a2) -> Double.compare(a1.giaTriThuc(), a2.giaTriThuc()));
        System.out.println("Phân số lớn nhất trong danh sách: ");
        maxPhanSo.hienThiPhanSo();

        Collections.sort(dsPS, (a1, a2) -> Double.compare(a1.giaTriThuc(), a2.giaTriThuc()));
        System.out.println("Danh sách sau khi sắp xếp tăng dần: ");
        for (PhanSo phanSo : dsPS) {
            phanSo.hienThiPhanSo();
        }
    }
}
