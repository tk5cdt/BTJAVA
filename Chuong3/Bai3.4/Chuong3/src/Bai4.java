import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class Bai4 {
    private JTable table1;
    private JButton btnThemDM;
    private JButton btnCapNhatDM;
    private JButton btnXoaDM;
    private JTextField txtMaSP;
    private JTextField txtTenSP;
    private JTextField txtSoLuong;
    private JTextField txtDonGia;
    private JButton btnThemSP;
    private JButton btnCapNhatSP;
    private JButton btnXoaSP;
    private JList list1;
    private JComboBox cboDM;
    private JPanel panel1;
    Connection con = DBconnect.getInstance().getConnection();
    public String tenDM;
    private int previousSelectedRow = -1;
    public Bai4()
    {
        loadList();
        loadCbo();

        list1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                loadTable();
                String danhMucDuocChon = (String) list1.getSelectedValue();
                cboDM.setSelectedItem(danhMucDuocChon);

            }
        });
        table1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                int selectedRow = table1.getSelectedRow();

                if(selectedRow != -1)
                {
                    if(selectedRow == previousSelectedRow){
                        txtMaSP.setText("");
                        txtTenSP.setText("");
                        txtDonGia.setText("");
                        txtSoLuong.setText("");
                        table1.clearSelection();
                    }
                    else {
                        previousSelectedRow = selectedRow;
                        txtMaSP.setText(table1.getValueAt(selectedRow, 0).toString());
                        txtTenSP.setText(table1.getValueAt(selectedRow, 1).toString());
                        txtSoLuong.setText(table1.getValueAt(selectedRow, 2).toString());
                        txtDonGia.setText(table1.getValueAt(selectedRow, 3).toString());
                    }

                }
            }
        });

        btnThemDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmThemDM frm1 = new frmThemDM();
                frm1.setLocationRelativeTo(null);
                frm1.setBai4Instance(Bai4.this);
                frm1.setTitle("Thêm danh mục");
                frm1.pack();
                frm1.setVisible(true);
            }
        });

        btnCapNhatDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selected = list1.getSelectedIndex();
                if(selected == -1){
                    JOptionPane.showMessageDialog(null, "Chọn danh mục cần cập nhật");
                }
                else {
                    tenDM = (String) list1.getModel().getElementAt(selected);
                    frmCapNhatDM frm2 = new frmCapNhatDM(tenDM);
                    frm2.setLocationRelativeTo(null);
                    frm2.setBai4Instance(Bai4.this);
                    frm2.setTitle("Cập nhật danh mục");
                    frm2.pack();
                    frm2.setVisible(true);
                }
            }
        });

        btnXoaDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    int selected = list1.getSelectedIndex();
                    if(selected == -1){
                        JOptionPane.showMessageDialog(null, "Chọn danh mục cần xóa");
                    }
                    else {
                        tenDM = (String) list1.getModel().getElementAt(selected);
                        String sql = "UPDATE DANHMUC SET ISDELETE = 1 WHERE TENDM = N'"+tenDM+"'";
                        Statement statement = con.createStatement();
                        int kq = statement.executeUpdate(sql);
                        if(kq>0)
                        {
                            JOptionPane.showMessageDialog(null, "Xóa thành công");
                            String sql1 = "SELECT MADM FROM DANHMUC WHERE TENDM = N'"+tenDM+"'";
                            ResultSet rs = statement.executeQuery(sql1);
                            String maDM = "";
                            if(rs.next())
                            {
                                maDM = rs.getString("MADM");
                                String sql2 = "UPDATE SANPHAM SET ISDELETE = 1 WHERE MADM = '"+maDM+"'";
                                statement = con.createStatement();
                                statement.executeUpdate(sql2);
                            }
                            loadList();
                            loadCbo();
                            loadTable();
                        }
                    }
                }catch (SQLException ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        btnThemSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if ((txtTenSP.getText().equals("")) || (txtSoLuong.getText().equals("")) || (txtDonGia.getText().equals(""))){
                        JOptionPane.showMessageDialog(null, "Chưa nhập sản phẩm");
                    }else {
                        String sql = "SELECT TENSP FROM SANPHAM where TENSP=N'"+txtTenSP.getText()+"'";
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null,"Sản phẩm này đã tồn tại!");
                        }else {
                            String tenDM = cboDM.getSelectedItem().toString();
                            String sql2 = "SELECT MADM FROM DANHMUC WHERE TENDM = N'"+tenDM+"'";
                            rs = statement.executeQuery(sql2);
                            String maDM = "";
                            if(rs.next())
                            {
                                maDM = rs.getString("MADM");
                                String sql1 = "INSERT INTO SANPHAM(TENSP, SOLUONG, DONGIA, MADM, ISDELETE) VALUES (N'"+txtTenSP.getText()+"', "+txtSoLuong.getText()+","+txtDonGia.getText()+", '"+maDM+"', 0)";
                                statement = con.createStatement();
                                int kq = statement.executeUpdate(sql1);
                                if(kq > 0){
                                    JOptionPane.showMessageDialog(null, "Thêm thành công");
                                    loadTable();
                                    txtMaSP.setText("");
                                    txtTenSP.setText("");
                                    txtDonGia.setText("");
                                    txtSoLuong.setText("");
                                }
                            }
                        }
                    }
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        btnCapNhatSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if ((txtTenSP.getText().equals("")) || (txtSoLuong.getText().equals("")) || (txtDonGia.getText().equals(""))){
                        JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm cần cập nhật!");
                    }else {
                        String sql = "SELECT TENSP FROM SANPHAM where TENSP=N'"+txtTenSP.getText()+"'";
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        if(rs.next()){
                            JOptionPane.showMessageDialog(null,"Sản phẩm này đã tồn tại!");
                        }else {
                            String sql1 = "UPDATE SANPHAM SET TENSP = N'"+txtTenSP.getText()+"', DONGIA = "+txtDonGia.getText()+", SOLUONG = "+txtSoLuong.getText()+" WHERE MASP = '"+txtMaSP.getText()+"'";
                            statement = con.createStatement();
                            int kq = statement.executeUpdate(sql1);
                            if(kq > 0){
                                JOptionPane.showMessageDialog(null, "Cập nhật thành công");
                                loadTable();
                                txtMaSP.setText("");
                                txtTenSP.setText("");
                                txtDonGia.setText("");
                                txtSoLuong.setText("");
                            }
                        }
                    }
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });

        btnXoaSP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if ((txtTenSP.getText().equals("")) || (txtSoLuong.getText().equals("")) || (txtDonGia.getText().equals(""))){
                        JOptionPane.showMessageDialog(null, "Chưa chọn sản phẩm để xóa!");
                    }else {
                        String sql = "UPDATE SANPHAM SET ISDELETE = 1 WHERE MASP = '"+txtMaSP.getText()+"'";
                        Statement statement = con.createStatement();
                        int kq = statement.executeUpdate(sql);
                        if(kq > 0){
                            JOptionPane.showMessageDialog(null, "Xóa thành công");
                            loadTable();
                            txtMaSP.setText("");
                            txtTenSP.setText("");
                            txtDonGia.setText("");
                            txtSoLuong.setText("");
                        }
                    }
                }catch (Exception ex){
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame("Quản lý sản phẩm");
        frame.setContentPane(new Bai4().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void loadList()
    {
        DefaultListModel<String> model = new DefaultListModel<>();
        try{
            String sql = "SELECT TENDM FROM DANHMUC WHERE ISDELETE = 0";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next())
            {
                model.addElement(rs.getString("TENDM"));
            }
            list1.setModel(model);
            rs.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void loadTable()
    {
        try{
            String danhMucDuocChon = (String) list1.getSelectedValue();
            String sql = "SELECT MASP, TENSP, SOLUONG, DONGIA FROM SANPHAM SP JOIN DANHMUC DM ON SP.MADM = DM.MADM WHERE DM.TENDM = ? AND SP.ISDELETE = 0";
            PreparedStatement preparedStatement = con.prepareStatement(sql);
            preparedStatement.setString(1, danhMucDuocChon);
            ResultSet rs = preparedStatement.executeQuery();

            // Column Names
            DefaultTableModel model = new DefaultTableModel(new Object[]{"Mã sản phẩm", "Tên sản phẩm", "Số lượng", "Đơn giá"}, 0);
            while (rs.next()){
                Object[] row = new Object[4];
                row[0] = rs.getString("MASP");
                row[1] = rs.getString("TENSP");
                row[2] = rs.getInt("SOLUONG");
                row[3] = rs.getFloat("DONGIA");
                model.addRow(row);
            }
            table1.setModel(model);
            preparedStatement.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void loadCbo()
    {
        try{
            String sql = "SELECT TENDM FROM DANHMUC WHERE ISDELETE = 0";
            Statement statement = con.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            DefaultComboBoxModel<String> model = new DefaultComboBoxModel<>();
            while (rs.next()){
                model.addElement(rs.getString("TENDM"));
            }
            cboDM.setModel(model);
            rs.close();
        }catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
