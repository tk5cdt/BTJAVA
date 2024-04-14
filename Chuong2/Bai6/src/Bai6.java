import javax.swing.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.io.PrintWriter;
public class Bai6 extends JFrame
{

    String fileName = "C:\\Users\\vothi\\Documents\\HK6\\Java\\BTJAVA\\Chuong2\\Bai6\\src\\Bai6.txt";

    boolean addNew = false;
    boolean changed = false;
    private JPanel panel1;
    private JComboBox cbo_PhongBan;
    private JTextField txt_Ms;
    private JTextField txt_HoTen;
    private JTextField txt_NgayVl;
    private JTextField txt_NamSinh;
    private JList lst_NV;
    private JButton btn_Luu;
    private JButton xóaButton;
    private JButton btnThoat;


    private List<PhongBan> danhSachPhongBan;
    private List<NhanVien> danhSachNhanVien;


    private void docDuLieuTuFileTxt() {
        danhSachPhongBan = new ArrayList<>();
        danhSachNhanVien = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File(fileName));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");

                if (parts[0].equals("PhongBan")) {
                    String maPhong = parts[1];
                    String tenPhong = parts[2];
                    danhSachPhongBan.add(new PhongBan(maPhong, tenPhong));
                } else if (parts[0].equals("NhanVien")) {
                    String maPhong= parts[1];
                    String maSo = parts[2];
                    String hoTen = parts[3];
                    String ngayVaoLam = parts[4];
                    String ngaySinh = parts[5];
                    danhSachNhanVien.add(new NhanVien(maPhong,maSo, hoTen, ngayVaoLam, ngaySinh));
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void loadData() {
        // Đọc dữ liệu từ file txt
        docDuLieuTuFileTxt();
        List<String> tenPhongBan = new ArrayList<>();
        for (PhongBan phongBan : danhSachPhongBan) {
            tenPhongBan.add(phongBan.getTenPhong());
        }
        cbo_PhongBan.setModel(new DefaultComboBoxModel<>(tenPhongBan.toArray(new String[0])));

        // Load employees for the first department by default
        if (!danhSachPhongBan.isEmpty()) {
            loadEmployeesByDepartment(danhSachPhongBan.get(0).getMaPhong());
        }
    }

    private void xuLySuKien() {
         // Xử lý sự kiện chọn phòng ban
        cbo_PhongBan.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = cbo_PhongBan.getSelectedIndex();
                String maPhong = danhSachPhongBan.get(selectedIndex).getMaPhong();
                loadEmployeesByDepartment(maPhong);
            }

        });

        // Xử lý sự kiện chọn nhân viên
        lst_NV.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedIndex = lst_NV.getSelectedIndex();
                    if (selectedIndex != -1)
                    {
                        String selectedEmployeeName = lst_NV.getSelectedValue().toString(); // Lấy tên nhân viên được chọn
                        String maSo = getMaSoByTenNhanVien(selectedEmployeeName); // Tìm mã số của nhân viên dựa trên tên
                        if (maSo != null) {
                            showEmployeeInfo(maSo); // Hiển thị thông tin của nhân viên
                        }
                    }
                }
            }
        });


    }
    private void loadEmployeesByDepartment(String maPhong) {
        List<String> tenNhanVien = new ArrayList<>();
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaPhong().equals(maPhong)) {
                tenNhanVien.add(nhanVien.getHoTen());
            }
        }
        lst_NV.setListData(tenNhanVien.toArray(new String[0]));
    }
    private void showEmployeeInfo(String maSo) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaSo().equals(maSo)) {
                // Hiển thị thông tin cá nhân của nhân viên
                txt_Ms.setText(nhanVien.getMaSo());
                txt_HoTen.setText(nhanVien.getHoTen());
                txt_NgayVl.setText(nhanVien.getNgayVaoLam());
                txt_NamSinh.setText(nhanVien.getNgaySinh());
                return; // Return after finding the employee
            }
        }
        // If the employee is not found, clear the text fields
        txt_Ms.setText("");
        txt_HoTen.setText("");
        txt_NgayVl.setText("");
        txt_NamSinh.setText("");
    }


    private String getMaSoByTenNhanVien(String tenNhanVien) {
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getHoTen().equals(tenNhanVien)) {
                return nhanVien.getMaSo();
            }
        }
        return null;
    }

    private void ghiDuLieuVaoFileTxt() {
        try (PrintWriter writer = new PrintWriter(new File(fileName))) {
            for (PhongBan phongBan : danhSachPhongBan) {
                writer.println("PhongBan," + phongBan.getMaPhong() + "," + phongBan.getTenPhong());
            }
            for (NhanVien nhanVien : danhSachNhanVien) {
                writer.println("NhanVien," + nhanVien.getMaPhong() + "," + nhanVien.getMaSo() + "," + nhanVien.getHoTen() + "," + nhanVien.getNgayVaoLam() + "," + nhanVien.getNgaySinh());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void xuLyLuuThongTin() {
        // Lấy thông tin cá nhân của nhân viên từ các text field
        String maSo = txt_Ms.getText();
        String hoTen = txt_HoTen.getText();
        String ngayVaoLam = txt_NgayVl.getText();
        String ngaySinh = txt_NamSinh.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maSo.isEmpty() || hoTen.isEmpty() || ngayVaoLam.isEmpty() || ngaySinh.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }

        // Cập nhật thông tin nhân viên
        for (NhanVien nhanVien : danhSachNhanVien) {
            if (nhanVien.getMaSo().equals(maSo)) {
                nhanVien.setHoTen(hoTen);
                nhanVien.setNgayVaoLam(ngayVaoLam);
                nhanVien.setNgaySinh(ngaySinh);
                break;
            }
        }

        // Lưu dữ liệu vào file
        ghiDuLieuVaoFileTxt();

        // Hiển thị thông báo thành công
        JOptionPane.showMessageDialog(null, "Lưu thông tin thành công!");
    }

    private void xuLyXoaNhanVien() {
        // Lấy thông tin nhân viên cần xóa
        String maSo = txt_Ms.getText();

        // Kiểm tra dữ liệu đầu vào
        if (maSo.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập mã số nhân viên!");
            return;
        }

        // Xác nhận với người dùng
        int confirmed = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn muốn xóa nhân viên " + maSo + "?", "Xác nhận", JOptionPane.YES_NO_OPTION);
        if (confirmed == JOptionPane.YES_OPTION) {
            // Xóa nhân viên khỏi danh sách
            for (int i = 0; i < danhSachNhanVien.size(); i++) {
                if (danhSachNhanVien.get(i).getMaSo().equals(maSo)) {
                    danhSachNhanVien.remove(i);
                    break;
                }
            }

            // Lưu thay đổi vào file
            ghiDuLieuVaoFileTxt();

            // Hiển thị thông báo thành công
            JOptionPane.showMessageDialog(null, "Xóa nhân viên thành công!");
            loadData();
        }
    }

    private void xuLyThoat() {
        // Đóng form
        dispose();
    }
    public  Bai6()
{
    setTitle("Quản lý nhân viên");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setContentPane(panel1);
    loadData();
    xuLySuKien();
    btn_Luu.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            xuLyLuuThongTin();
        }
    });
    xóaButton.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            xuLyXoaNhanVien();
        }
    });

// Thêm ActionListener cho nút "Thoát"
    btnThoat.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            xuLyThoat();
        }
    });

}
    public static void main(String[] args) {

        JFrame frame = new Bai6();
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }


    class PhongBan {
        private String maPhong;
        private String tenPhong;

        public PhongBan(String maPhong, String tenPhong) {
            this.maPhong = maPhong;
            this.tenPhong = tenPhong;
        }

        public String getMaPhong() {
            return maPhong;
        }

        public String getTenPhong() {
            return tenPhong;
        }
    }

    class NhanVien {
        private String maPhong;
        private String maSo;
        private String hoTen;
        private String ngayVaoLam;
        private String ngaySinh;

        public NhanVien(String maPhong, String maSo, String hoTen, String ngayVaoLam, String ngaySinh) {
            this.maPhong=maPhong;
            this.maSo = maSo;
            this.hoTen = hoTen;
            this.ngayVaoLam = ngayVaoLam;
            this.ngaySinh = ngaySinh;
        }
        public String getMaPhong() {
            return maPhong;
        }

        public String getMaSo() {
            return maSo;
        }

        public String getHoTen() {
            return hoTen;
        }

        public String getNgayVaoLam() {
            return ngayVaoLam;
        }

        public String getNgaySinh() {
            return ngaySinh;
        }

        public void setHoTen(String hoTen) {
            this.hoTen = hoTen;
        }

        public void setNgayVaoLam(String ngayVaoLam) {
            this.ngayVaoLam = ngayVaoLam;
        }
        public void setNgaySinh(String ngaySinh) {
            this.ngaySinh = ngaySinh;
        }
    }
}
