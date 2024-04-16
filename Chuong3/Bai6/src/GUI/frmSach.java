package GUI;

import DAO.DBConnect;
import DAO.LoaiSachDAO;
import DAO.SachDAO;
import POJO.LoaiSach;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
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

    DefaultComboBoxModel<LoaiSach> model;

    int id;
    Connection conn;

    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            ResultSet rs = SachDAO.instance.getSach();

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
        try {
            List<LoaiSach> loaiSaches = LoaiSachDAO.instance.getAll();
            model = new DefaultComboBoxModel<>();
            for (LoaiSach ls : loaiSaches) {
                model.addElement(ls);
            }
            cbbTheLoai.setModel(model);
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

    public int viTriCuaData(int idData)
    {
        try {
            return LoaiSachDAO.instance.getRowNum(idData);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public frmSach() {
        setTitle("Quản lý sách");
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
                try {
                    if(txtTieuDe.getText().equals("") || txtMoTa.getText().equals("") || txtTacGia.getText().equals("") || txtNamXB.getText().equals(""))
                        JOptionPane.showMessageDialog(btnThem,"Chưa nhập thông tin");
                    else
                    {
//                        StringBuffer sb = new StringBuffer();
//                        ResultSet rs = SachDAO.instance.timKiemSach(txtTieuDe.getText());
//                        if(rs.next())
//                        {
//                            JOptionPane.showMessageDialog(btnThem,"Sách này đã tồn tại");
//                        }
//                        else
//                        {
                            try {
                                Integer.parseInt(txtNamXB.getText());
                            } catch (NumberFormatException ex) {
                                JOptionPane.showMessageDialog(table1,"Bạn cần nhập số");
                                txtNamXB.setText("");
                            }
                            int kq = SachDAO.instance.themSach(txtTieuDe.getText(),txtTacGia.getText(),txtNamXB.getText(),((LoaiSach)cbbTheLoai.getSelectedItem()).getId()+"",txtMoTa.getText());
                            if(kq>0)
                            {
                                JOptionPane.showMessageDialog(table1,"Thêm mới thành công");
                                frmLoad();
                                cleanData();
                                cbbLoad();
                            }
//                        }
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

                btnSua.setVisible(true);
                btnXoa.setVisible(true);
                int row = table1.getSelectedRow();
                int column = table1.getSelectedColumn();
                // biến lấy id của thể loại
                int idTL;
                String tenTL = table1.getValueAt(row,4).toString();
                try {
                    LoaiSach loaiSach = LoaiSachDAO.instance.search(tenTL);
                    if (loaiSach != null)
                    {
                        idTL = loaiSach.getId();
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
                        int kq = SachDAO.instance.suaSach(id+"", txtTieuDe.getText(), txtTacGia.getText(), txtNamXB.getText(),((LoaiSach)cbbTheLoai.getSelectedItem()).getId()+"",txtMoTa.getText());
                        if(kq>0)
                        {
                            JOptionPane.showMessageDialog(table1,"Sửa thành công");
                            frmLoad();
                            cleanData();
                            cbbLoad();
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
                        int kq = SachDAO.instance.xoaSach(id + "");
                        if (kq > 0) {
                            JOptionPane.showMessageDialog(table1, "Xoá thành công");
                            frmLoad();
                            cleanData();
                            cbbLoad();
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
    
}