import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.nio.channels.SelectableChannel;
import java.nio.channels.Selector;
import java.sql.*;
import java.util.Vector;

public class frmLoaiSach extends JDialog {
    private JPanel contentPane;
    private JPanel panel1;
    private JTable table1;
    private JTextField txtTenSach;
    private JTextField txtMoTa;
    private JButton btnThem;
    private JButton btnSua;
    private JButton btnXoa;
    private JButton btnThoat;
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
            String sql = "select * from LOAISACH where XOA = 0";
            rs = statement.executeQuery(sql);

            // đọc dữ liệu
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã thể loại");
            dtm.addColumn("Thể loại");
            dtm.addColumn("Mô tả");
            while (rs.next()) {
                Vector vt = new Vector();
                vt.add(rs.getInt("LS_ID"));
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

    public frmLoaiSach() {
        setContentPane(contentPane);
        setModal(true);
        setTitle("Loại sách");
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

        dispose();

        btnThem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                conn = cn.getConnection();
                try {
                    if(txtTenSach.getText().equals("") || txtMoTa.getText().equals(""))
                        JOptionPane.showMessageDialog(table1,"Chưa nhập thông tin");
                    else
                    {
                        StringBuffer sb = new StringBuffer();
                        String qCheck = "select TEN from LOAISACH where TEN=N'"+txtTenSach.getText()+"'";
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(qCheck);
                        if(rs.next())
                        {
                            sb.append("Loại sách này đã tồn tại");
                        }
                        if(sb.length()>0)
                        {
                            JOptionPane.showMessageDialog(table1,sb.toString());
                        }
                        else
                        {
                            String q = "insert into LOAISACH values (N'"+txtTenSach.getText()+"',N'"+txtMoTa.getText()+"',0)";
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
        btnSua.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtTenSach.getText().equals("")||txtMoTa.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(table1,"dữ liệu trống");
                    }
                    else
                    {
                        StringBuffer sb = new StringBuffer();
                        String qCheck = "select TEN from LOAISACH where TEN=N'"+txtTenSach.getText()+"' and MOTA=N'"+txtMoTa.getText()+"'";
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(qCheck);
                        if(rs.next())
                        {
                            sb.append("Loại sách này đã tồn tại");
                        }
                        if(sb.length()>0)
                        {
                            JOptionPane.showMessageDialog(table1,sb.toString());
                        }
                        else
                        {
                            String q = "UPDATE LOAISACH " +
                                    "SET TEN = N'"+txtTenSach.getText()+"', MOTA = N'"+txtMoTa.getText()+"' " +
                                    "WHERE LS_ID = "+id+"";
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
                }
                catch (Exception ex)
                {

                }
            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                // lấy data
                btnSua.setVisible(true);
                btnXoa.setVisible(true);
                int row = table1.getSelectedRow();
                int column = table1.getSelectedColumn();
                id = (int)table1.getModel().getValueAt(row,0);
                txtTenSach.setText(table1.getModel().getValueAt(row,1).toString());
                txtMoTa.setText(table1.getModel().getValueAt(row,2).toString());
            }
        });
        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtTenSach.getText().equals("")||txtMoTa.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(table1,"dữ liệu trống");
                    }
                    else
                    {
                        StringBuffer sb = new StringBuffer();
                        String qCheck = "select TEN from LOAISACH where TEN=N'"+txtTenSach.getText()+"'";
                        Statement st = conn.createStatement();
                        ResultSet rs = st.executeQuery(qCheck);
                        if(!rs.next())
                        {
                            JOptionPane.showMessageDialog(table1,"Loại sách chưa tồn tại");
                        }
                        else
                        {
                            String q = "UPDATE LOAISACH SET XOA=1" +
                                    "WHERE LS_ID = "+id+"";
                            st = conn.createStatement();
                            int kq = st.executeUpdate(q);
                            if(kq>0)
                            {
                                JOptionPane.showMessageDialog(table1,"Xoá thành công");
                                frmLoad();
                                cleanData();
                            }
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

    public void cleanData()
    {
        txtTenSach.setText("");
        txtMoTa.setText("");
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
        frmLoaiSach dialog = new frmLoaiSach();
        dialog.pack();
        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
        System.exit(0);
    }
}
