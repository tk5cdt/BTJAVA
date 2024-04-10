import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;

public class Bai1 {
    private JTextField txtTen;
    private JTextField txtMa;
    private JTextField txtNS;
    private JButton btnThem;
    private JTable tblKH;
    private JButton btnLuu;
    private JButton btnStart;
    private JButton btnEnd;
    private JButton btnNext;
    private JButton btnPre;
    private JButton btnXoa;
    private JPanel panel1;

    Connection con;
    public Bai1(){

        loadData();
        txtMa.requestFocus();
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmpty(txtMa.getText()) || !checkIsEmpty(txtTen.getText()) || !checkIsEmpty(txtNS.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin khách hàng trước khi thêm!");
                }
                else
                    addCustomer(txtMa.getText(), txtTen.getText(), Integer.parseInt(txtNS.getText()));
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Delete(txtMa.getText());
            }
        });
        tblKH.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tblKH.getSelectedRow();
                if (row != -1) {
                    String maKhachHang = tblKH.getValueAt(row, 0).toString();
                    String tenKhachHang = tblKH.getValueAt(row, 1).toString();
                    String namSinh = tblKH.getValueAt(row, 2).toString();

                    txtMa.setText(maKhachHang);
                    txtTen.setText(tenKhachHang);
                    txtNS.setText(namSinh);
                }
            }
        });

        btnLuu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmpty(txtMa.getText()) || !checkIsEmpty(txtTen.getText()) || !checkIsEmpty(txtNS.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin khách hàng trước khi thêm!");
                }
                else
                    Update(txtMa.getText(), txtTen.getText(), txtNS.getText());
            }
        });
        btnStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                tblKH.setRowSelectionInterval(0,0);
                tblKH.scrollRectToVisible(tblKH.getCellRect(0,0,true));
            }
        });
        btnEnd.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int lastRow = tblKH.getRowCount() - 1;
                tblKH.setRowSelectionInterval(lastRow, lastRow);
                tblKH.scrollRectToVisible(tblKH.getCellRect(lastRow, 0, true));

            }
        });
        btnNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblKH.getSelectedRow();
                int nextRow = (selectedRow == tblKH.getRowCount() - 1) ? 0 : selectedRow + 1;
                tblKH.setRowSelectionInterval(nextRow, nextRow);
                tblKH.scrollRectToVisible(tblKH.getCellRect(nextRow, 0, true));

            }
        });
        btnPre.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tblKH.getSelectedRow();
                int prevRow = (selectedRow == 0) ? tblKH.getRowCount() - 1 : selectedRow - 1;
                tblKH.setRowSelectionInterval(prevRow, prevRow);
                tblKH.scrollRectToVisible(tblKH.getCellRect(prevRow, 0, true));
            }
        });
    }
    public void loadData()
    {
        new DBConnect("DESKTOP-KFOVQS4", "1433", "QL_KHACHHANG");

        try{
            String sql = "select makh, tenkh, namsinh from KHACHHANG";
            ResultSet resultSet = DBConnect.getInstance().executeQuery(sql);
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã khách hàng", "Tên khách hàng", "Năm sinh"}, 0);

            while (resultSet.next())
            {
                Object[] row = new Object[3];
                row[0] = resultSet.getString("MAKH");
                row[1] = resultSet.getString("TENKH");
                row[2] = resultSet.getString("NAMSINH");
                model.addRow(row);

            }
            tblKH.setModel(model);
            resultSet.close();
       } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void Delete(String makh) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = new DBConnect("DESKTOP-KFOVQS4", "1433", "QL_KHACHHANG").getConnection();
            String sql = "DELETE FROM khachhang WHERE makh = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công khách hàng có mã: " + makh);
                loadData();
                Reset();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có mã: " + makh, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa khách hàng có mã: " + makh + "\nChi tiết lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    public boolean checkIsEmpty(String aa)
    {
        if(aa.isEmpty())
            return false;
        return true;
    }
    public void Reset()
    {
        txtMa.setText("");
        txtTen.setText("");
        txtNS.setText("");
        txtMa.requestFocus();
    }
    public void addCustomer(String makh, String tenkh, int namsinh) {
        con = new DBConnect("DESKTOP-KFOVQS4", "1433", "QL_KHACHHANG").getConnection();
        String sql = "select count(*) from KHACHHANG where makh = ?";
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, makh);
            resultSet = statement.executeQuery();
            resultSet.next();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        int count = 0;
        try {
            count = resultSet.getInt(1);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (count > 0) {
            JOptionPane.showMessageDialog(null, "Trùng id");
            txtMa.requestFocus();
        }
        String sql2 = "INSERT INTO KHACHHANG(MAKH, TENKH, NAMSINH) VALUES(?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql2);
            ps.setString(1, makh);
            ps.setString(2, tenkh);
            ps.setInt(3, namsinh);

            int row = ps.executeUpdate();
            if (row > 0) {
                JOptionPane.showMessageDialog(null, "Thêm thành công");
                loadData();
                Reset();
            } else {
                JOptionPane.showMessageDialog(null, "Thêm không thành công");
            }

            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void Update(String makh, String tenKhachHang, String namSinh) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = new DBConnect("DESKTOP-KFOVQS4", "1433", "QL_KHACHHANG").getConnection();
            String sql = "UPDATE khachhang SET tenkh = ?, namsinh = ? WHERE makh = ?";
            statement = con.prepareStatement(sql);

            statement.setString(1, tenKhachHang);
            statement.setString(2, namSinh);
            statement.setString(3, makh);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công thông tin khách hàng có mã: " + makh);
                loadData();
                Reset();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có mã: " + makh, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin khách hàng có mã: " + makh + "\nChi tiết lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        JFrame frame = new JFrame("QuanLyNhanVien");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Bai1().panel1);
        frame.pack();
        frame.setVisible(true);
    }

}

