package GUI;

import DAO.DBConnect;
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

    JDateChooser dateChooser = new JDateChooser();
    Connection conn;

    Date date;
    String strDate;

    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            ResultSet rs = null;
            String sql = "select * from HOADON where XOA = 0";
            rs = DBConnect.getInstance().executeQuery(sql);

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
        String q = "select TIEUDE, SOLUONG, GIABAN from SACH s join CHITIETHOADON ct on s.S_ID = ct.IDSACH and ct.IDHOADON ="+idhd+"";
        try {
            ResultSet rs = DBConnect.getInstance().executeQuery(q);
            while (rs.next())
            {
                //System.out.println("Tên sách: "+rs.getString("0")+"\nSố lượng: "+rs.getInt("SOLUONG")+"\nGía bán: "+rs.getFloat("GIABAN")+"");
                JOptionPane.showMessageDialog(table1,"Chi tiết hoá đơn:\nTên sách: "+rs.getString("TIEUDE")+"\nSố lượng: "+rs.getInt("SOLUONG")+"\nGía bán: "+rs.getFloat("GIABAN")+"");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public frmHoaDon(String tenNV) {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        dateChooser.setDateFormatString("dd-MM-yyyy");
        JNgay.add(dateChooser);

//        SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
//        String date = dcn.format(dateChooser.getDate() );
//        lbDate.setText(date.toString());

        txtNhanVien.setText(tenNV);

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
                        String q = "INSERT INTO HOADON (NGAY, NHANVIEN, TENKHACHHANG, GHICHU, XOA) VALUES " +
                                "('"+date+"', N'"+txtNhanVien.getText()+"', N'"+txtTenKH.getText()+"', N'"+txtGhiChu.getText()+"', 0)";
                        int kq = DBConnect.getInstance().executeUpdate(q);
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
                // biến lấy id của thể loại
                int idHD;
                String q = "select HD_ID from HOADON where HD_ID = "+id+"";
                try {
                    ResultSet rs = DBConnect.getInstance().executeQuery(q);
//                    while (rs.next())
//                    {
//                        idHD = rs.getInt("HD_ID");
//                        //chiTietHoaDon(idHD);
//                    }
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
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

//        dateChooser.addComponentListener(new ComponentAdapter() {
//            @Override
//            public void componentResized(ComponentEvent e) {
//                super.componentResized(e);
//                strDate = JNgay.toString();
//                System.out.println(strDate);
//            }
//        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String date = dcn.format(dateChooser.getDate());
                String q = "UPDATE HOADON SET NGAY = '"+date+"', NHANVIEN = N'"+txtNhanVien.getText()+
                        "', TENKHACHHANG = N'"+txtTenKH.getText()+"', GHICHU = N'"+txtGhiChu.getText()+"'" +
                        "WHERE HD_ID = "+id+"";
                try {
                    int kq = DBConnect.getInstance().executeUpdate(q);
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
                    String q = "UPDATE HOADON SET XOA = 1 WHERE HD_ID = "+id+"";
                    int kq;
                    kq = DBConnect.getInstance().executeUpdate(q);
                    if(kq > 0)
                    {
                        JOptionPane.showMessageDialog(table1,"Xoá thành công");
                        frmLoad();
                        cleanData();
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
        frmHoaDon dialog = new frmHoaDon("");
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
