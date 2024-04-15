package GUI;

import DAO.DBConnect;
import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

public class frmPhieuNhapSach extends JDialog {
    private JPanel contentPane;
    private JPanel panel1;
    private JTable table1;
    private JTextField txtNhanVien;
    private JTextField txtGhiChu;
    private JPanel JNgay;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnThoat;
    private JButton buttonOK;
    private int id;

    JDateChooser dateChooser = new JDateChooser();


    Date date;
    String strDate;

    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            ResultSet rs = null;
            String sql = "select * from PHIEUNHAPSACH where XOA = 0";
            rs = DBConnect.getInstance().executeQuery(sql);

            // đọc dữ liệu
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã phiếu");
            dtm.addColumn("Ngày");
            dtm.addColumn("Nhân viên");
            dtm.addColumn("Ghi chú");
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getInt("PNS_ID"));
                vt.add(rs.getString("NGAY"));
                vt.add(rs.getString("NHANVIEN"));
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
        txtNhanVien.setText("");
        txtGhiChu.setText("");
        JNgay.removeAll();
        dateChooser.setDate(new Date());
        JNgay.add(dateChooser);
    }

    public void chiTietPhieuNhap(int idpn)
    {
        String q = "select TIEUDE, SOLUONG, DONGIA from CHITIETPHIEUNHAPSACH c join SACH s on c.IDSACH = s.S_ID and c.CTPNS_ID ="+idpn+"";
        try {
            ResultSet rs = DBConnect.getInstance().executeQuery(q);
            while (rs.next())
            {
                //System.out.println("Tên sách: "+rs.getString("0")+"\nSố lượng: "+rs.getInt("SOLUONG")+"\nGía bán: "+rs.getFloat("GIABAN")+"");
                JOptionPane.showMessageDialog(table1,"Chi tiết phiếu nhập sách:\nTên sách: "+rs.getString("TIEUDE")+"\nSố lượng: "+rs.getInt("SOLUONG")+"\nĐơn giá: "+rs.getFloat("DONGIA")+"");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public frmPhieuNhapSach() {
        dateChooser.setDateFormatString("dd-MM-yyyy");
        JNgay.add(dateChooser);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        frmLoad();
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtNhanVien.getText().equals(""))
                        JOptionPane.showMessageDialog(table1,"Chưa nhập thông tin");
                    else
                    {
                        SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                        String date = dcn.format(dateChooser.getDate());
                        String q = "INSERT INTO PHIEUNHAPSACH (GHICHU, NGAY, NHANVIEN, XOA)\n" +
                                "VALUES (N'"+txtGhiChu.getText()+"', '"+date+"', N'"+txtNhanVien.getText()+"', 0)";
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
                String q = "select PNS_ID from PHIEUNHAPSACH where PNS_ID = "+id+"";
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
                chiTietPhieuNhap(id);
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
                txtGhiChu.setText(table1.getModel().getValueAt(row,3).toString());
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                String date = dcn.format(dateChooser.getDate());
                String q = "UPDATE PHIEUNHAPSACH SET NGAY = '"+date+"', NHANVIEN = N'"+txtNhanVien.getText()+
                        "', GHICHU = N'"+txtGhiChu.getText()+"'" +
                        "WHERE PNS_ID = "+id+"";
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
                    String q = "UPDATE PHIEUNHAPSACH SET XOA = 1 WHERE PNS_ID = "+id+"";
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
        frmPhieuNhapSach dialog = new frmPhieuNhapSach();
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
