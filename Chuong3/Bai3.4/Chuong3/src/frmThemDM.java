import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class frmThemDM extends JFrame{
    private JTextField txtTenDM;
    private JButton btnAddDM;
    private JPanel pnThemDM;

    private Bai4 bai4Instance;

    public void setBai4Instance(Bai4 bai4Instance) {
        this.bai4Instance = bai4Instance;
    }
    Connection con = DBconnect.getInstance().getConnection();

    public frmThemDM(){
        setContentPane(pnThemDM);
        btnAddDM.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    if ((txtTenDM.getText().equals(""))){
                        JOptionPane.showMessageDialog(btnAddDM, "Chưa nhập tên danh mục!");
                    }else {
                        String sql = "SELECT TENDM FROM DANHMUC where TENDM=N'"+txtTenDM.getText()+"'";
                        Statement statement = con.createStatement();
                        ResultSet rs = statement.executeQuery(sql);
                        if(rs.next()){
                            JOptionPane.showMessageDialog(btnAddDM,"Danh mục này đã tồn tại!");
                        }else {
                            String sql1 = "INSERT INTO DANHMUC(TENDM, ISDELETE) VALUES (N'"+txtTenDM.getText()+"', 0)";
                            statement = con.createStatement();
                            int kq = statement.executeUpdate(sql1);
                            if(kq > 0){
                                JOptionPane.showMessageDialog(btnAddDM, "Thêm thành công");
                                bai4Instance.loadList();
                                bai4Instance.loadCbo();
                                txtTenDM.setText("");
                            }
                        }
                    }
                }catch (Exception ex){
                    throw new RuntimeException(ex);
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
