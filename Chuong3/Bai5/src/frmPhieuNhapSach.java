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
    private String tenNV;

    JDateChooser dateChooser = new JDateChooser();

    ConnectDB cn = new ConnectDB();
    Connection conn;

    Date date;
    String strDate;

    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            conn = cn.getConnection();
            Statement statement = null;
            ResultSet rs = null;
            statement = conn.createStatement();
            String sql = "select * from PHIEUNHAPSACH where XOA = 0";
            rs = statement.executeQuery(sql);

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
        conn = cn.getConnection();
        String q = "select TIEUDE, SOLUONG, DONGIA from CHITIETPHIEUNHAPSACH c join SACH s on c.IDSACH = s.S_ID and c.CTPNS_ID ="+idpn+"";
        try {
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(q);
            StringBuffer sb = new StringBuffer();
            while (rs.next())
            {
                sb.append("Tên sách: "+rs.getString("TIEUDE")+"\nSố lượng: "+rs.getInt("SOLUONG")+"\nGiá nhập: "+rs.getInt("DONGIA")+"\n\n");
            }
            JOptionPane.showMessageDialog(table1,sb.toString(), "Chi tiết phiếu nhập", JOptionPane.INFORMATION_MESSAGE);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public frmPhieuNhapSach(String tenNV) {
        dateChooser.setDateFormatString("dd-MM-yyyy");
        JNgay.add(dateChooser);
        setTitle("Phiếu nhập sách");
        txtNhanVien.setText(tenNV);
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        frmLoad();
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = cn.getConnection();
                try {
                    if(txtNhanVien.getText().equals(""))
                        JOptionPane.showMessageDialog(table1,"Chưa nhập thông tin");
                    else
                    {
                        SimpleDateFormat dcn = new SimpleDateFormat("yyyy-MM-dd");
                        String date = dcn.format(dateChooser.getDate());
                        String q = "INSERT INTO PHIEUNHAPSACH (GHICHU, NGAY, NHANVIEN, XOA)\n" +
                                "VALUES (N'"+txtGhiChu.getText()+"', '"+date+"', N'"+txtNhanVien.getText()+"', 0)";
                        Statement st = conn.createStatement();
                        int kq = st.executeUpdate(q);
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
                Statement st = null;
                try {
                    st = conn.createStatement();
                    ResultSet rs = st.executeQuery(q);
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
                conn = cn.getConnection();
                String q = "UPDATE PHIEUNHAPSACH SET NGAY = '"+date+"', NHANVIEN = N'"+txtNhanVien.getText()+
                        "', GHICHU = N'"+txtGhiChu.getText()+"'" +
                        "WHERE PNS_ID = "+id+"";
                try {
                    Statement st = conn.createStatement();
                    int kq = st.executeUpdate(q);
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
                conn = cn.getConnection();
                try {
                    String q = "UPDATE PHIEUNHAPSACH SET XOA = 1 WHERE PNS_ID = "+id+"";
                    Statement st = conn.createStatement();
                    int kq;
                    kq = st.executeUpdate(q);
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
        frmPhieuNhapSach dialog = new frmPhieuNhapSach("");
        dialog.setLocationRelativeTo(null);
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
