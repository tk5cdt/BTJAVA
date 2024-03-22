import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;


public class Bai5 extends Component {
    private JButton btnRegister;
    private JButton btnReset;
    private JTextField txtName;
    private JTextField txtDoB;
    private JTextField txtEmail;
    private JRadioButton rdMale;
    private JRadioButton rdFM;
    private JComboBox cboPro;
    private JPanel pannelMain;

    JFrame frame = new JFrame("Bai5");
    public Bai5() {
        ThongTin tt = new ThongTin();
        ArrayList<ThongTin> list = new ArrayList<>();
        txtName.setToolTipText("Nhập ít nhất 3 kí tự");
        txtDoB.setToolTipText("Định dạng dd/MM/yyyy");
        txtEmail.setToolTipText("Định dạng abc@gmail.com");
        btnReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtName.setText("");
                txtDoB.setText("");
                txtEmail.setText("");
                rdMale.setSelected(true);
                cboPro.setSelectedIndex(0);

                txtName.requestFocus();
            }
        });
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ghiFile(tt);
                txtName.setText("");
                txtDoB.setText("");
                txtEmail.setText("");
                rdMale.setSelected(true);
                cboPro.setSelectedIndex(0);
                txtName.requestFocus();
            }
        });

    }
    public boolean checkDateOfBirth()
    {
        SimpleDateFormat date = new SimpleDateFormat("dd/MM/yyyy");
        date.setLenient(false);
        try{
            date.parse(txtDoB.getText());

        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, "Sai ròi kìa! Nhập lại đúng định dạng dd/MM/yyyy!");

            txtDoB.requestFocus();
            return false;
        }
        return true;
    }
    public boolean checkEmail()
    {
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@" + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        String email = txtEmail.getText();
        if(!patternMatches(email, regexPattern))
        {
            JOptionPane.showMessageDialog(frame, "Sai ròi kìa! Nhập lại đúng abc@gmail.com!");
            txtEmail.requestFocus();
            return false;
        }
        return true;
    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }

    public boolean checkEmpty()
    {
        if(txtName.getText().isEmpty() || txtDoB.getText().isEmpty() || txtEmail.getText().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Không được để trống!");
            txtName.requestFocus();
            return false;
        }
        return true;
    }

    public void ghiFile(ThongTin tt) {
        FileWriter file = null;
        try {
            file = new FileWriter("DuLieu.txt", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (checkEmpty() && checkDateOfBirth() && checkEmail()) {
            tt = new ThongTin();
            tt.setName(txtName.getText());
            System.out.println(tt.getName());
            tt.setDateofbirth(txtDoB.getText());

            tt.setEmail(txtEmail.getText());
            String sex = rdFM.isSelected() ? "Nữ" : "Nam";
            tt.setSex(sex);
            tt.setProvince(String.valueOf(cboPro.getSelectedItem()));

            try (PrintWriter pw = new PrintWriter(file)) {
                pw.println(tt.getName());

                pw.println(tt.getDateofbirth());
                pw.println(tt.getEmail());
                pw.println(tt.getSex());
                pw.println(tt.getProvince());

                pw.flush();
                pw.close();
                JOptionPane.showMessageDialog(frame, "Thêm thông tin thành công!");
            } catch (Exception e) {
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bai5");
        frame.setContentPane(new Bai5().pannelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
