package Bai34;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Bai34 {
    static File file = new File("");
    private static final String FILE_NAME = file.getAbsolutePath() + "\\Chuong1\\src\\Bai34\\danhba.txt";

    public static void main(String[] args) throws IOException {
        List<DanhBa> danhBaList = new ArrayList<>();

        // Nhập danh sách danh bạ từ bàn phím
        nhapDanhBa(danhBaList);

        // Xuất danh sách danh bạ ra màn hình
        xuatDanhBa(danhBaList);

        // Ghi danh sách danh bạ vào file
        ghiDanhBaVaoFile(danhBaList);

        // Đọc danh sách danh bạ từ file
        List<DanhBa> danhBaListFromFile = docDanhBaTuFile();

        // Xuất danh sách danh bạ đọc từ file
        System.out.println("\nDanh sách danh bạ đọc từ file:");
        xuatDanhBa(danhBaListFromFile);
    }

    private static void nhapDanhBa(List<DanhBa> danhBaList) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Nhập số lượng danh bạ: ");
        int n = scanner.nextInt();
        scanner.nextLine();

        for (int i = 0; i < n; i++) {
            System.out.println("Nhập thông tin danh bạ thứ " + (i + 1));
            System.out.print("Tên: ");
            String ten = scanner.nextLine();
            System.out.print("Số điện thoại: ");
            String soDienThoai = scanner.nextLine();

            danhBaList.add(new DanhBa(ten, soDienThoai));
        }
    }

    private static void xuatDanhBa(List<DanhBa> danhBaList) {
        for (DanhBa danhBa : danhBaList) {
            System.out.println(danhBa);
        }
    }

    private static void ghiDanhBaVaoFile(List<DanhBa> danhBaList) throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream(FILE_NAME);
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        ) {
            objectOutputStream.writeObject(danhBaList);
        }
    }

    private static List<DanhBa> docDanhBaTuFile() throws IOException {
        List<DanhBa> danhBaList = new ArrayList<>();

        try (
                FileInputStream fileInputStream = new FileInputStream(FILE_NAME);
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        ) {
            danhBaList = (List<DanhBa>) objectInputStream.readObject();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return danhBaList;
    }
}
