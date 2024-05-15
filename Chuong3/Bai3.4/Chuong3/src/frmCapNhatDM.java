import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class frmCapNhatDM extends JFrame{
    private JTextField txtTenDM;
    private JButton btnUpdateDM;
    private JPanel pnUpdateDM;
    Connection con = DBconnect.getInstance().getConnection();
    private Bai4 bai4Instance; // Thêm biến để lưu tham chiếu của Bai4

    public void setBai4Instance(Bai4 bai4Instance) {
        this.bai4Instance = bai4Instance;
    }
    public String tenDanhMuc;
    public frmCapNhatDM(String tenDM)
    {
        setContentPane(pnUpdateDM);
        txtTenDM.setText(tenDM);
        tenDanhMuc = txtTenDM.getText();
        btnUpdateDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(txtTenDM.getText().equals(""))
                    {
                        JOptionPane.showMessageDialog(btnUpdateDM,"Dữ liệu trống");
                    }
                    else
                    {
                        String sql = "SELECT TENDM FROM DANHMUC where TENDM=N'"+txtTenDM.getText()+"'";
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        if(rs.next()){
                            JOptionPane.showMessageDialog(btnUpdateDM,"Danh mục nay đã tồn tại!");
                        }
                        else
                        {
                            String sql2 = "SELECT MADM FROM DANHMUC WHERE TENDM = N'"+tenDM+"'";
                            rs = statement.executeQuery(sql2);
                            String maDM = "";
                            if(rs.next())
                            {
                                maDM = rs.getString("MADM");
                                String sql1 = "UPDATE DANHMUC SET TENDM = N'"+txtTenDM.getText()+"' WHERE MADM = '"+maDM+"'";
                                statement = con.createStatement();
                                int kq = statement.executeUpdate(sql1);
                                if(kq>0)
                                {
                                    JOptionPane.showMessageDialog(btnUpdateDM, "Cập nhật thành công");
                                    bai4Instance.loadList();
                                    bai4Instance.loadCbo();
                                    setVisible(false);
                                }
                            }
                        }
                    }
                }
                catch (Exception ex)
                {

                }
            }
        });
    }

    public static void main(String[] args){
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
