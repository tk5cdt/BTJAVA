import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public class frmSach extends JDialog {
    private JPanel contentPane;
    private JPanel panel1;
    private JTable table1;
    private JTextField txtTieuDe;
    private JTextField txtTacGia;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnThoat;
    private JTextField txtNamXB;
    private JTextField txtMoTa;
    private JComboBox cbbTheLoai;
    private JButton buttonOK;
    private JButton buttonCancel;

    int id;

    ConnectDB cn = new ConnectDB();
    Connection conn;

    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            conn = cn.getConnection();
            Statement statement = null;
            ResultSet rs = null;
            statement = conn.createStatement();
            String sql = "select S_ID,TIEUDE,TACGIA,NAMXUATBAN,ls.TEN,s.MOTA from SACH s join LOAISACH ls on s.THELOAI = ls.LS_ID and XOA = 0";
            rs = statement.executeQuery(sql);

            // đọc dữ liệu
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã sách");
            dtm.addColumn("Tiêu đề");
            dtm.addColumn("Tác giả");
            dtm.addColumn("Năm xuất bản");
            dtm.addColumn("Thể loại");
            dtm.addColumn("Mô tả");
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getInt("S_ID"));
                vt.add(rs.getString("TIEUDE"));
                vt.add(rs.getString("TACGIA"));
                vt.add(rs.getString("NAMXUATBAN"));
                vt.add(rs.getString("TEN"));
                vt.add(rs.getString("MOTA"));
                dtm.addRow(vt);
            }
            this.table1.setModel(dtm);
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
    }

    public void cbbLoad()
    {
        conn = cn.getConnection();
        Statement statement = null;
        try {
            statement = conn.createStatement();
            ResultSet rs = null;
            String sql1 = "select LS_ID, TEN from LOAISACH";
            rs = statement.executeQuery(sql1);
            while (rs.next())
            {
                Theloai tl = new Theloai(rs.getInt("LS_ID"), rs.getString("TEN"));
                cbbTheLoai.addItem(tl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void cleanData()
    {
        txtTieuDe.setText("");
        txtTacGia.setText("");
        txtNamXB.setText("");
        cbbTheLoai.setSelectedIndex(-1);
        txtMoTa.setText("");
    }

    public  int viTriCuaData(int idData)
    {
        int vt;
        conn = cn.getConnection();
        try {
            Statement st = conn.createStatement();
            String q = "select * from View_LS_ID_With_RowNum where LS_ID = "+idData+"";
            ResultSet rs = st.executeQuery(q);
            while (rs.next())
            {
                vt = rs.getInt("SoThuTu");
                return vt;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
    public frmSach() {
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(buttonOK);

        // call onCancel() when cross is clicked
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        contentPane.registerKeyboardAction(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        }, KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        frmLoad();
        cbbLoad();

        dispose();
        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = cn.getConnection();
                try {
                    if(txtTieuDe.getText().equals("") || txtMoTa.getText().equals("") || txtTacGia.getText().equals("") || txtNamXB.getText().equals(""))
                        JOptionPane.showMessageDialog(table1,"Chưa nhập thông tin");
                    else
                    {
                        StringBuffer sb = new StringBuffer();
                        String qCheck = "select TIEUDE from SACH where TIEUDE = N'"+txtTieuDe.getText()+"' and XOA = 0";
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(qCheck);
                        if(rs.next())
                        {
                            JOptionPane.showMessageDialog(table1,"Sách này đã tồn tại");
                        }
                        else
                        {
                            try {
                                Integer.parseInt(txtNamXB.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(table1,"Bạn cần nhập số");
                                txtNamXB.setText("");
                            }
                            String q = "INSERT INTO SACH (TIEUDE, TACGIA, NAMXUATBAN, THELOAI, MOTA, XOA)\n" +
                                    "VALUES (N'"+txtTieuDe.getText()+"', N'"+txtTacGia.getText()+"', "+txtNamXB.getText()+", "+((Theloai)cbbTheLoai.getSelectedItem()).getId()+
                                    ", N'"+txtMoTa.getText()+"', 0)";
                            st = conn.createStatement();
                            int kq = st.executeUpdate(q);
                            if(kq>0)
                            {
                                JOptionPane.showMessageDialog(table1,"Thêm mới thành công");
                                frmLoad();
                                cleanData();
                            }
                        }
                    }
                }
                catch (Exception ex)
                {
                    System.out.print(e);
                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                conn = cn.getConnection();

                btnSua.setVisible(true);
                btnXoa.setVisible(true);
                int row = table1.getSelectedRow();
                int column = table1.getSelectedColumn();
                // biến lấy id của thể loại
                int idTL;
                String tenTL = table1.getValueAt(row,4).toString();
                String q = "select LS_ID from LOAISACH where TEN = N'"+tenTL+"'";
                Statement st = null;
                Theloai TL;
                try {
                    st = conn.createStatement();
                    ResultSet rs = st.executeQuery(q);
                    while (rs.next())
                    {
                        idTL = rs.getInt("LS_ID");
                        TL=new Theloai(idTL,tenTL);
                        cbbTheLoai.setSelectedIndex(viTriCuaData(idTL)-1);
                    }
                }
                catch (SQLException ex)
                {
                    throw new RuntimeException(ex);
                }
                id = (int)table1.getModel().getValueAt(row,0);
                txtTieuDe.setText(table1.getModel().getValueAt(row,1).toString());
                txtTacGia.setText(table1.getModel().getValueAt(row,2).toString());
                txtNamXB.setText(table1.getModel().getValueAt(row,3).toString());
                txtMoTa.setText(table1.getModel().getValueAt(row,5).toString());
            }
        });
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try
                {
                    if(txtTieuDe.getText().equals("") || txtMoTa.getText().equals("") || txtTacGia.getText().equals("") || txtNamXB.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(btnSua,"dữ liệu trống");
                    }
                    else
                    {
                        StringBuffer sb = new StringBuffer();
                        String qCheck = "select TIEUDE from SACH where TIEUDE=N'"+txtTieuDe.getText()+"' and S_ID != "+id+" and XOA = 0";
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(qCheck);

                        String q = "UPDATE SACH " +
                                "SET TIEUDE = N'"+txtTieuDe.getText()+"',TACGIA = N'"+txtTacGia.getText()+"',NAMXUATBAN = "+txtNamXB.getText()+",THELOAI = "+
                                ((Theloai)cbbTheLoai.getSelectedItem()).getId()+", MOTA = N'"+txtMoTa.getText()+"' " +
                                "WHERE S_ID = "+id+"";
                        st = conn.createStatement();
                        int kq = st.executeUpdate(q);
                        if(kq>0)
                        {
                            JOptionPane.showMessageDialog(table1,"Sửa thành công");
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
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = cn.getConnection();
                try {
                    String q = "UPDATE SACH SET XOA = 1 WHERE S_ID = "+id+"";
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

    private void onOK() {
        // add your code here
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        frmSach dialog = new frmSach();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }

    public class Theloai {
        int id;
        String ten;

        public Theloai(int id, String ten) {
            this.id = id;
            this.ten = ten;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTen() {
            return ten;
        }

        public void setTen(String ten) {
            this.ten = ten;
        }

        @Override
        public String toString() {
            return ten;
        }
    }
}