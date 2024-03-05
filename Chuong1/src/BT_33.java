/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bt_chuong1;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
/**
 *
 * @author admin
 */
class KhachHang implements Serializable {
    private String maSo;
    private String hoTen;
    private int namSinh;

    public KhachHang(String maSo, String hoTen, int namSinh) {
        this.maSo = maSo;
        this.hoTen = hoTen;
        this.namSinh = namSinh;
    }

    @Override
    public String toString() {
        return "ma so: " + maSo + ", ho ten: " + hoTen + ", nam sinh: " + namSinh;
    }

}

public class BT_33 {
    public static void main(String[] args) {
        ArrayList<KhachHang> danhSachKhachHang = new ArrayList<>();

        nhapDanhSachKhachHang(danhSachKhachHang);

        xuatDanhSachKhachHang(danhSachKhachHang);

        ghiDanhSachKhachHangRaFile(danhSachKhachHang);

        ArrayList<KhachHang> danhSachKhachHangTuFile = docDanhSachKhachHangTuFile();
        System.out.println("\ndanh sach khach hang tu file:");
        xuatDanhSachKhachHang(danhSachKhachHangTuFile);
    }

    public static void nhapDanhSachKhachHang(ArrayList<KhachHang> danhSach) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("nhap thong tin khach hang:");
        System.out.print("nhap so luong khach hang: ");
        int soLuong = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < soLuong; i++) {
            System.out.println("khach hang thu " + (i + 1) + ":");
            System.out.print("ma so: ");
            String maSo = scanner.nextLine();
            System.out.print("ho ten: ");
            String hoTen = scanner.nextLine();
            System.out.print("nam sinh: ");
            int namSinh = scanner.nextInt();
            scanner.nextLine();

            KhachHang khachHang = new KhachHang(maSo, hoTen, namSinh);
            danhSach.add(khachHang);
        }
    }

    public static void xuatDanhSachKhachHang(ArrayList<KhachHang> danhSach) {
        System.out.println("\ndanh sach khach hang:");
        for (KhachHang khachHang : danhSach) {
            System.out.println(khachHang);
        }
    }

    public static void ghiDanhSachKhachHangRaFile(ArrayList<KhachHang> danhSach) {
    try {
        FileOutputStream fileOut = new FileOutputStream("danhsachkhachhang.txt");
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut);
        for (KhachHang khachHang : danhSach) {
            objectOut.writeObject(khachHang);
        }
        objectOut.close();
        fileOut.close();
        System.out.println("\ndanh sach da duoc ghi ra file.");
    } catch (IOException e) {
        e.printStackTrace();
    }
}

    public static ArrayList<KhachHang> docDanhSachKhachHangTuFile() {
        ArrayList<KhachHang> danhSach = new ArrayList<>();
        try {
            FileInputStream fileIn = new FileInputStream("danhsachkhachhang.txt");
            ObjectInputStream objectIn = new ObjectInputStream(fileIn);
            Object obj;
            while ((obj = objectIn.readObject()) != null) {
                if (obj instanceof KhachHang) {
                    danhSach.add((KhachHang) obj);
                }
            }
            objectIn.close();
            fileIn.close();
        } catch (EOFException e) {
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return danhSach;
    }

}
