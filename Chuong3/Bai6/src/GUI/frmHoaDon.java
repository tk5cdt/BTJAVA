package GUI;

import DAO.DBConnect;
import DAO.HoaDonDAO;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class frmHoaDon extends JDialog {
    private JPanel contentPane;
    private JPanel panel1;
    private JTable table1;
    private JTextField txtNhanVien;
    private JTextField txtTenKH;
    private JTextField txtGhiChu;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnThoat;
    private JPanel JNgay;
    private JButton buttonOK;
    private int id;
    private String idNV;

    JDateChooser dateChooser = new JDateChooser();
    Connection conn;

    Date date;
    String strDate;

    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            ResultSet rs = HoaDonDAO.instance.getHoaDon();
            // đọc dữ liệu
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã hoá đơn");
            dtm.addColumn("Ngày");
            dtm.addColumn("Nhân viên");
            dtm.addColumn("Tên khách hàng");
            dtm.addColumn("Ghi chú");
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getInt("HD_ID"));
                vt.add(rs.getString("NGAY"));
                vt.add(rs.getString("NHANVIEN"));
                vt.add(rs.getString("TENKHACHHANG"));
                vt.add(rs.getString("GHICHU"));
                dtm.addRow(vt);
            }
            this.table1.setModel(dtm);
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }

    public void cleanData()
    {
        txtTenKH.setText("");
        txtNhanVien.setText("");
        txtGhiChu.setText("");
        JNgay.removeAll();
        dateChooser.setDate(new Date());
        JNgay.add(dateChooser);
    }

    public void chiTietHoaDon(int idhd)
    {
        try {
            ResultSet rs = HoaDonDAO.instance.getHoaDonTheoMa(idhd+"");
            StringBuffer sb = new StringBuffer();
            while (rs.next())
            {
                sb.append("Tên sách: "+rs.getString("TIEUDE")+"\nSố lượng: "+rs.getInt("SOLUONG")+"\nGía bán: "+rs.getFloat("GIABAN")+"\n\n");
            }
                JOptionPane.showMessageDialog(table1,sb.toString(), "Chi tiết hoá đơn", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public frmHoaDon(String tenNV, String idNV) {
        setTitle("Hoá đơn");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        dateChooser.setDateFormatString("dd-MM-yyyy");
        JNgay.add(dateChooser);

        txtNhanVien.setText(tenNV);
        this.idNV = idNV;

        frmLoad();
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtTenKH.getText().equals("") || txtNhanVien.getText().equals(""))
                        JOptionPane.showMessageDialog(table1,"Chưa nhập thông tin");
                    else
                    {
                        SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                        String date = dcn.format(dateChooser.getDate());
                        int kq = HoaDonDAO.instance.themHoaDon(date, txtNhanVien.getText(), txtTenKH.getText(), txtGhiChu.getText());
                        if(kq>0)
                        {
                            JOptionPane.showMessageDialog(table1,"Thêm mới thành công");
                            frmLoad();
                            cleanData();
                        }
                    }
                }
                catch (Exception ex)
                {
                    System.out.print(ex);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                btnSua.setVisible(true);
                btnXoa.setVisible(true);
                int row = table1.getSelectedRow();
                int column = table1.getSelectedColumn();
                id = (int)table1.getModel().getValueAt(row,0);
                chiTietHoaDon(id);
                String day = table1.getModel().getValueAt(row,1).toString();
                try
                {
                    DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    Date startDate = (Date)formatter.parse(day);
                    dateChooser.setDate(startDate);
                }
                catch (Exception ex)
                {}

                txtNhanVien.setText(table1.getModel().getValueAt(row,2).toString());
                txtTenKH.setText(table1.getModel().getValueAt(row,3).toString());
                txtGhiChu.setText(table1.getModel().getValueAt(row,4).toString());
            }
        });

        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String date = dcn.format(dateChooser.getDate());
                try {
                    int kq = HoaDonDAO.instance.suaHoaDon(id+"", date, txtNhanVien.getText(), txtTenKH.getText(), txtGhiChu.getText());
                    if(kq > 0)
                        JOptionPane.showMessageDialog(table1,"Sửa thành công");
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
                frmLoad();
                cleanData();
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int row = table1.getSelectedRow();
                    id = (int)table1.getModel().getValueAt(row,0);
                    if (row == -1) {
                        JOptionPane.showMessageDialog(table1, "Chưa chọn dữ liệu cần xóa");
                    }
                    else if (JOptionPane.showConfirmDialog(table1, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                        return;
                    }
                    else {
                        int kq = HoaDonDAO.instance.xoaHoaDon(id + "");
                        if (kq > 0) {
                            JOptionPane.showMessageDialog(table1, "Xoá thành công");
                            frmLoad();
                            cleanData();
                        }
                    }
                }
                catch (Exception ex)
                {

                }
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        frmHoaDon dialog = new frmHoaDon("","");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
