
import java.util.Random;
import java.util.Scanner;

public class Bai22 {

    static Scanner sc = new Scanner(System.in);

    public static void showMenu() {
        System.out.println("================== MENU ==================");
        System.out.println("01. Nhập mảng từ bàn phím");
        System.out.println("02. Tạo mảng ngẫu nhiên");
        System.out.println("03. Xuất mảng");
        System.out.println("04. Liệt kê giá trị âm");
        System.out.println("05. Liệt kê số nguyên tố");
        System.out.println("06. Liệt kê các phần tử trong đoạn [a, b]");
        System.out.println("07. Tổng các số nguyên tố");
        System.out.println("08. Trung bình cộng các số dương");
        System.out.println("09. Đếm số phần tử lớn hơn x");
        System.out.println("10. Đếm số phần tử là số nguyên tố");
        System.out.println("11. Kiểm tra mảng toàn số nguyên tố");
        System.out.println("12. Kiểm tra mảng tăng dần");
        System.out.println("13. Tìm giá trị lớn nhất");
        System.out.println("14. Tìm giá trị nhỏ nhất");
        System.out.println("15. Tìm số âm lớn nhất");
        System.out.println("16. Đảo ngược mảng");
        System.out.println(" 0. Thoát chương trình");
        System.out.println("==========================================");
    }

    public static void main(String[] args) {
        int a[] = new int[0];
        int n = 0, chon = 0;
        int x, y;
        x = y = 0;
        showMenu();
        do {
            System.out.print("\nChọn chức năng: ");
            chon = sc.nextInt();
            switch (chon) {
                case 1:
                    System.out.print("Nhập số phần tử: ");
                    n = sc.nextInt();
                    a = new int[n];
                    nhapMang(a, n);
                    xuatMang(a);
                    break;
                case 2:
                    System.out.print("Nhập số phần tử: ");
                    n = sc.nextInt();
                    a = new int[n];
                    taoMangNgauNhien(a, n);
                    xuatMang(a);
                   break;
                case 3:
                    xuatMang(a);
                    break;
                case 4:
                    lietKeSoAm(a);
                    break;
                case 5:
                    lietKeSoNguyenTo(a);
                    break;
                case 6:
                    System.out.println("Nhập đoạn [a, b] muốn tìm: ");
                    System.out.print("a = ");
                    x = sc.nextInt();
                    System.out.print("b = ");
                    y = sc.nextInt();
                    lietKeSoTrongDoanA_B(a, x, y);
                    break;
                case 7:
                    
                    break;
                case 8:

                    break;
                case 9:

                    break;
                case 10:

                    break;
                case 11:

                    break;
                case 12:

                    break;
                case 13:

                    break;
                case 14:

                    break;
                case 15:

                    break;
                case 16:

                    break;
                default:

                    break;
            }

        } while (chon != 0);

    }

    public static void nhapMang(int a[], int n) {
        System.out.println("Nhập giá trị cho từng phần tử");
        for (int i = 0; i < n; i++) {
            System.out.print("a[" + i + "]: ");
            a[i] = sc.nextInt();
        }
    }

    public static void taoMangNgauNhien(int a[], int n) {
        Random r = new Random();
        for (int i = 0; i < n; i++) {
            a[i] = r.nextInt(200 + 199) - 199;
        }
    }

    public static void xuatMang(int a[]) {
        System.out.println("Mảng hiện tại:");
        for (int i : a) {
            System.out.print(i + " ");
        }
        System.out.println("");
    }

    public static void lietKeSoAm(int a[]) {
        System.out.println("Danh sách số âm: ");
        for (int i : a) {
            if (i < 0) {
                System.out.print(i + " ");
            }
        }
    }

    public static boolean kiemTraSoNguyenTo(int n) {
        if (n < 2) {
            return false;
        }
        for (int i = 2; i <= n / 2; i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void lietKeSoNguyenTo(int a[]) {
        System.out.println("Danh sách số nguyên tố: ");
        for (int i : a) {
            if (kiemTraSoNguyenTo(i)) {
                System.out.print(i + " ");
            }
        }
    }

    public static void lietKeSoTrongDoanA_B(int a[], int x, int y) {
        System.out.println("Danh sách các số trong đoạn ["+x +", "+ y+"]: ");
        for (int i : a) {
            if (i >=x && i <=y) {
                System.out.print(i + " ");
            }
        }
    }
    public static int tinhTongPhanTuLaSoNguyenTo(int a[]) {
        int sum = 0;
        for(int i:a)
        {
            if(kiemTraSoNguyenTo(i))
                sum+=i;
        }
        return sum;
    }
}
