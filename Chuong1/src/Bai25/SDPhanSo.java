package Bai25;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;


public class SDPhanSo {


    public static void menu() {
        System.out.println("-------------------MENU-------------------");
        System.out.println("0. Thoat");
        System.out.println("1. Tao hai phan so a va b");
        System.out.println("2. Tao 2 phan so x va y nhap tu ban phim");
        System.out.println("3. Hien thi gia tri nghich dao cua phan so x");
        System.out.println("4. Tinh tong x + y");
        System.out.println("5. Nhap vao 1 danh sach gom n phan so");
        System.out.println("6. Tinh tong n phan so do");
        System.out.println("7. Tim phan so lon nhat trong n phan so");
        System.out.println("8. Sap xep danh sach phan so theo thu tu tang dan");
    }

    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);

        int chon, n;
        PhanSo x = new PhanSo(), y = new PhanSo();
        ArrayList<PhanSo> ds = new ArrayList<>();
        do {
            menu();
            do {
                System.out.print("Chon chuc nang muon thuc hien: ");
                chon = sc.nextInt();
            } while (chon < 0 || chon > 8);

            switch (chon) {
                case 0: {
                    System.out.println("Bye!");
                    break;
                }
                case 1:{
                    PhanSo a = new PhanSo(3, 7);
                    PhanSo b = new PhanSo(4, 9);
                    System.out.print("a = ");
                    a.hienThiPhanSo();
                    System.out.print("b = ");
                    b.hienThiPhanSo();
                    break;
                }
                case 2: {
                    System.out.print("Nhap tu so x: ");
                    int tuX = sc.nextInt();
                    System.out.print("Nhap mau so x: ");
                    int mauX = sc.nextInt();
                    x = new PhanSo(tuX, mauX);

                    System.out.print("Nhap tu so y: ");
                    int tuY = sc.nextInt();
                    System.out.print("Nhap mau so y: ");
                    int mauY = sc.nextInt();
                    y = new PhanSo(tuY, mauY);
                    break;
                }
                case 3: {
                    PhanSo nghichDaoX = x.giaTriNghichDao();
                    System.out.println("Nghich dao cua x la: ");
                    nghichDaoX.hienThiPhanSo();
                    break;
                }
                case 4: {
                    PhanSo sumXY = x.cong(y);
                    System.out.print("x + y = ");
                    sumXY.hienThiPhanSo();
                    break;
                }
                case 5:{
                    System.out.print("Nhap so luong phan so: ");
                    n = sc.nextInt();
                    for (int i=0; i<n; i++){
                        System.out.print("Nhap tu so cua so thu " + (i+1) + ": ");
                        int tuSo = sc.nextInt();
                        System.out.print("Nhap mau so cua so thu " + (i+1) + ": ");
                        int mauSo = sc.nextInt();
                        ds.add(new PhanSo(tuSo, mauSo));
                    }
                    break;
                }
                case 6:
                {
                    PhanSo sum = new PhanSo();
                    for (PhanSo ps : ds){
                        sum = sum.cong(ps);
                    }
                    System.out.print("Tong n phan so: ");
                    sum.hienThiPhanSo();
                    break;
                }
                case 7:
                {
                    PhanSo max = ds.get(0);
                    for (PhanSo ps : ds){
                        if (ps.compareTo(max) > 0){
                            max = ps;
                        }
                    }
                    System.out.print("Phan so lon nhat la: ");
                    max.hienThiPhanSo();
                    break;
                }
                case 8:{
                    for (int i = 0; i< ds.size() - 1; i++){
                        for (int j = i+1; j<ds.size(); j++){
                            if(ds.get(i).compareTo(ds.get(j)) > 0)
                            {
                                PhanSo temp = ds.get(i);
                                ds.set(i, ds.get(j));
                                ds.set(j, temp);
                            }
                        }
                    }
                    System.out.println("Danh sach phan so sau khi sap xep: ");
                    for (PhanSo ps : ds)
                    {
                        ps.hienThiPhanSo();
                    }
                    break;
                }
            }
        } while (chon != 0);

    }


import Bai30.MonHoc;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
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
