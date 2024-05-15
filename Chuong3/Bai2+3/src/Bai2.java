import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.EventListener;
public class Bai2 {
    private JTextField txt_Ma;
    private JTextField txt_TieuDe;
    private JTextField txt_Loai;
    private JTextField txt_NamXuatBan;
    private JButton bt_Them;
    private JButton btn_Luu;
    private JButton btn_Xoa;
    private JButton btn_TimKiem;
    private JTable tbl;
    private JPanel panelB2;
    Connection con;
    public Bai2(){

        TimKiem tk = new TimKiem();
        tk.pack();

        loadData();
        txt_Ma.requestFocus();

        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbl.getSelectedRow();
                if (row != -1) {
                    String ma = tbl.getValueAt(row, 0).toString();
                    String tieuDe = tbl.getValueAt(row, 1).toString();
                    String loai = tbl.getValueAt(row, 2).toString();
                    String namXuatBan = tbl.getValueAt(row, 3).toString();

                    txt_Ma.setText(ma);
                    txt_TieuDe.setText(tieuDe);
                    txt_Loai.setText(loai);
                    txt_NamXuatBan.setText(namXuatBan);
                }
            }
        });

        bt_Them.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmpty(txt_Ma.getText()) || !checkIsEmpty(txt_TieuDe.getText()) || !checkIsEmpty(txt_Loai.getText()) || !checkIsEmpty(txt_NamXuatBan.getText()) )
                {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin sản phẩm trước khi thêm!");
                }
                else
                    add(txt_Ma.getText(), txt_TieuDe.getText(),txt_Loai.getText() ,Integer.parseInt(txt_NamXuatBan.getText()));
            }
        });
        btn_Xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Delete(txt_Ma.getText());
            }
        });
        tbl.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int row = tbl.getSelectedRow();
                if (row != -1) {
                    String ma = tbl.getValueAt(row, 0).toString();
                    String tieuDe = tbl.getValueAt(row, 1).toString();
                    String loai = tbl.getValueAt(row, 2).toString();
                    String namXuatBan = tbl.getValueAt(row, 3).toString();

                    txt_Ma.setText(ma);
                    txt_TieuDe.setText(tieuDe);
                    txt_Loai.setText(loai);
                    txt_NamXuatBan.setText(namXuatBan);
                }
            }
        });

        btn_Luu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!checkIsEmpty(txt_Ma.getText()) || !checkIsEmpty(txt_TieuDe.getText()) || !checkIsEmpty(txt_Loai.getText())|| !checkIsEmpty(txt_NamXuatBan.getText()))
                {
                    JOptionPane.showMessageDialog(null, "Hãy nhập đầy đủ thông tin sản phẩm trước khi lưu!");
                }
                else
                    Update(txt_Ma.getText(), txt_TieuDe.getText(), txt_Loai.getText(),Integer.parseInt(txt_NamXuatBan.getText()));
            }
        });

        btn_TimKiem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    tk.setLocationRelativeTo(null);
                    tk.setVisible(true);

            }
        });

    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("QuanLy CD DVD");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new Bai2().panelB2);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }

    public void loadData()
    {
        new DBConnect();

        try {
            String sql = "select * from CDDVDCollection";
            ResultSet resultSet = DBConnect.getInstance().executeQuery(sql);

            // Set column titles before adding data rows
            DefaultTableModel model = new DefaultTableModel(null, new Object[]{"Mã", "Tiêu đề", "Loại", "Năm xuất bản"});
            tbl.setModel(model);

            while (resultSet.next()) {
                Object[] row = new Object[4];
                row[0] = resultSet.getString("Ma");
                row[1] = resultSet.getString("TieuDe");
                row[2] = resultSet.getString("LoaiDia");
                row[3] = resultSet.getString("NamXuatBan");

                // Add data rows after setting column titles
                model.addRow(row);
            }

            resultSet.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void Delete(String ma) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = new DBConnect().getConnection();
            String sql = "DELETE FROM CDDVDCollection WHERE Ma = ?";
            statement = con.prepareStatement(sql);
            statement.setString(1, ma);
            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Xóa thành công khách hàng có mã: " + ma);
                loadData();
                Reset();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có mã: " + ma, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }

            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi xóa khách hàng có mã: " + ma + "\nChi tiết lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }
    }
    public boolean checkIsEmpty(String a)
    {
        if(a.isEmpty())
            return false;
        return true;
    }
    public void Reset()
    {
        txt_Ma.setText("");
        txt_Loai.setText("");
        txt_TieuDe.setText("");
        txt_NamXuatBan.setText("");
    }
    public void add(String ma, String tieude,String loai ,int namxuatban) {
        con = new DBConnect().getConnection();
        String sql = "select count(*) from CDDVDCollection where Ma = ?";
        PreparedStatement statement = null;
        ResultSet resultSet;

        try {
            statement = con.prepareStatement(sql);
            statement.setString(1, ma);
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
            txt_Ma.requestFocus();
        }
        String sql2 = "INSERT INTO CDDVDCollection(Ma, TieuDe, LoaiDia,NamXuatBan) VALUES(?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql2);
            ps.setString(1, ma);
            ps.setString(2, tieude);
            ps.setString(3, loai);
            ps.setInt(4, namxuatban);

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
    public void Update(String ma, String tieude,String loai ,int namxuatban) {
        Connection con = null;
        PreparedStatement statement = null;

        try {
            con = new DBConnect().getConnection();
            String sql = "UPDATE CDDVDCollection SET TieuDe = ?, LoaiDia = ?,NamXuatBan = ? WHERE Ma = ?";
            statement = con.prepareStatement(sql);

            statement.setString(1, tieude);
            statement.setString(2, loai);
            statement.setInt(3, namxuatban);
            statement.setString(4, ma);

            int rowsAffected = statement.executeUpdate();

            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Cập nhật thành công thông tin khách hàng có mã: " + ma);
                loadData();
                Reset();
            } else {
                JOptionPane.showMessageDialog(null, "Không tìm thấy khách hàng có mã: " + ma, "Lỗi", JOptionPane.ERROR_MESSAGE);
            }
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Lỗi khi cập nhật thông tin khách hàng có mã: " + ma  + "\nChi tiết lỗi: " + e.getMessage(), "Lỗi", JOptionPane.ERROR_MESSAGE);
            throw new RuntimeException(e);
        }

    }



}
