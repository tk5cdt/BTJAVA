import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Bai3 extends javax.swing.JFrame {
    double a, b;

    private JButton xButton;
    private JButton tButton;
    private JButton gButton;
    private JTextField txtNhapA;
    private JTextField txtNhapB;
    private JPanel panel1;
    private JRadioButton congBtn;
    private JRadioButton truBtn;
    private JRadioButton nhanBtn;
    private JRadioButton chiaBtn;
    private JTextField txtKQ;

    public boolean validateForm()
    {
        if(txtNhapA.getText().isEmpty()||txtNhapB.getText().isEmpty())
            return false;
        return true;
    }
    public Bai3() {
        setLocationRelativeTo(null);
        //congBtn.setSelected(true);
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        panel1.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                super.componentResized(e);
            }
        });
        gButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(validateForm())
                    a = Double.parseDouble(txtNhapA.getText());
                    b = Double.parseDouble(txtNhapB.getText());
                    if(congBtn.isSelected())
                    {
                        txtKQ.setText(""+(a+b));
                    }
                    else if(truBtn.isSelected())
                    {
                        txtKQ.setText(""+(a-b));
                    }
                    else if(nhanBtn.isSelected())
                    {
                        txtKQ.setText(""+(a*b));
                    }
                    else if(chiaBtn.isSelected())
                    {
                        txtKQ.setText("" + (a / b));
                    }
                    else
                        txtKQ.setText("bạn chưa chọn phép tính");


            }
        });
        xButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtNhapA.setText("");
                txtNhapB.setText("");
                txtKQ.setText("");
            }
        });
        tButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new Bai3().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
