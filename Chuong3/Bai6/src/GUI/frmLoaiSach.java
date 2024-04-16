package GUI;

import DAO.DBConnect;
import DAO.LoaiSachDAO;
import POJO.LoaiSach;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.sql.*;
import java.util.List;
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
    Connection conn;
    public void frmLoad()
    {
        btnSua.setVisible(false);
        btnXoa.setVisible(false);
        try {
            List<LoaiSach> loaiSaches = LoaiSachDAO.instance.getAll();

            // đọc dữ liệu
            DefaultTableModel dtm = new DefaultTableModel();
            dtm.addColumn("Mã sách");
            dtm.addColumn("Thể loại");
            dtm.addColumn("Mô tả");
            for (LoaiSach loaiSach : loaiSaches) {
                Vector<Object> vec = new Vector<>();
                vec.add(loaiSach.getId());
                vec.add(loaiSach.getTen());
                vec.add(loaiSach.getMoTa());
                dtm.addRow(vec);
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
                try {
                    if(txtTenSach.getText().equals("") || txtMoTa.getText().equals(""))
                        JOptionPane.showMessageDialog(table1,"Chưa nhập thông tin");
                    else
                    {
                        StringBuffer sb = new StringBuffer();
                        LoaiSach ls = LoaiSachDAO.instance.search(txtTenSach.getText());
                        if(ls != null)
                        {
                            sb.append("Loại sách này đã tồn tại");
                        }
                        if(sb.length()>0)
                        {
                            JOptionPane.showMessageDialog(table1,sb.toString());
                        }
                        else
                        {
                            int kq = LoaiSachDAO.instance.add(new LoaiSach(0, txtTenSach.getText(), txtMoTa.getText()));
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
                        if(LoaiSachDAO.instance.isExist(txtTenSach.getText(),id))
                        {
                            sb.append("Loại sách này đã tồn tại");
                        }
                        if(sb.length()>0)
                        {
                            JOptionPane.showMessageDialog(table1,sb.toString());
                        }
                        else {
                            LoaiSach loaiSach = new LoaiSach(id, txtTenSach.getText(), txtMoTa.getText());
                            int kq = LoaiSachDAO.instance.update(loaiSach);
                            if (kq > 0) {
                                JOptionPane.showMessageDialog(table1, "Sửa thành công");
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
                    int row = table1.getSelectedRow();
                    if (row == -1) {
                        JOptionPane.showMessageDialog(table1, "Chưa chọn dữ liệu cần xóa");
                    }
                    else if (JOptionPane.showConfirmDialog(table1, "Bạn có chắc chắn muốn xóa không?", "Xác nhận", JOptionPane.YES_NO_OPTION) != JOptionPane.YES_OPTION) {
                        return;
                    }
                    else
                    {
                        int kq = LoaiSachDAO.instance.delete(id);
                        if(kq>0)
                        {
                            JOptionPane.showMessageDialog(table1,"Xoá thành công");
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
