package Bai30;

import Bai27.DaGiac;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai30 {
    public static void main(String[] args) {
        List<MonHoc> dsmh = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập số lượng môn học: ");
        int n = scanner.nextInt();

        int chonMH = 0; // 0: LT; 1:TH; 2:DA
        for (int i = 0; i < n; i++) {
            System.out.println("Chọn loại môn học (0:LT; 1: TH; 2: DA): ");
            chonMH = scanner.nextInt();
            if (chonMH == 0) {
                MonHoc x = new LiThuyet();
                x.nhapTT();
                dsmh.add(x);
            } else if (chonMH == 1) {
                MonHoc x = new ThucHanh();
                x.nhapTT();
                dsmh.add(x);
            } else if (chonMH == 2) {
                MonHoc x = new DoAn();
                x.nhapTT();
                dsmh.add(x);
            }
        }

        System.out.println("Danh sách môn học vừa nhập vào: ");
        for (MonHoc x : dsmh)
            x.xuatTT();
        System.out.println("--------------------------------------------------");
    }
}
