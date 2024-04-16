package GUI;

import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

import DAO.DBConnect;
import DAO.DangNhapDAO;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.Connection;
import java.sql.Statement;

public class frmDangNhap extends JDialog {
    private JPanel contentPane;
    private JButton btnDangNhap;
    private JButton btnThoat;
    private JTextField txtTenDangNhap;
    private JTextField txtMatKhau;
    private JLabel lbUserName;
    private JLabel lbPass;
    private JPanel panel1;
    private JPanel mnThongTin;
    private String strTenDangNhap;
    private String strMatKhau;
    private String strTenNguoiDung;
    private String idNguoiDung;

    public String getIdNguoiDung() {
        return idNguoiDung;
    }

    private Boolean bKetQuaDangNhap;

    public String getStrMatKhau()
    {
        return strMatKhau;
    }

    public String getStrTenNguoiDung()
    {
        return strTenNguoiDung;
    }

    public boolean getKetQuaDangNhap()
    {
        return bKetQuaDangNhap;
    }

    public frmDangNhap() {
        setTitle("Đăng nhập");
        setContentPane(contentPane);
        setModal(true);
        getRootPane().setDefaultButton(btnDangNhap);

        btnDangNhap.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onOK();
            }
        });

        btnThoat.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                onCancel();
            }
        });

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
    }

    private void onOK() {
        // add your code here
        if (txtTenDangNhap.getText().isEmpty() || txtMatKhau.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Vui lòng nhập đầy đủ thông tin!");
            return;
        }
        try {
            ResultSet rs = DangNhapDAO.instance.login(txtTenDangNhap.getText(), txtMatKhau.getText());
            rs.next();
            if(rs.getRow() > 0) {
                strTenNguoiDung = rs.getString("HOTEN");
                strMatKhau = rs.getString("MATKHAU");
                idNguoiDung = rs.getString("ND_ID");
                bKetQuaDangNhap = true;
                this.setVisible(false);
            }
            else {
                JOptionPane.showMessageDialog(this,"tên đăng nhập hoặc mật khẩu không chính xác!");
            }
        }
        catch (Exception e)
        {
            System.out.print(e);
            //JOptionPane.showMessageDialog(this,e.getMessage());
        }
        dispose();
    }

    private void onCancel() {
        // add your code here if necessary
        dispose();
    }

    public static void main(String[] args) {
        frmDangNhap dialog = new frmDangNhap();
        dialog.pack();
        dialog.setVisible(true);
        System.exit(0);
    }
}
