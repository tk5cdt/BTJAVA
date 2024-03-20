import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai1 {
    private JPanel panel1;
    private JTextField txtA;
    private JTextField txtb;
    private JButton btnXoa;
    private JButton btngiai;
    private JButton btnThoat;
    private JTextField txtKQ;

    public Bai1() {

        btngiai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String inA = txtA.getText().trim();
                String inB = txtb.getText().trim();

                if(inA.isEmpty() || inB.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số a và số b");
                    txtA.requestFocus();
                    return;
                }

                if (!isNumeric(inA)) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số a hợp lệ");
                    txtA.requestFocus();
                    return;
                }

                if (!isNumeric(inB)) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số b hợp lệ");
                    txtb.requestFocus();
                    return;
                }
                float a = Float.parseFloat(inA);
                float b = Float.parseFloat(inB);

                if (a == 0) {
                    JOptionPane.showMessageDialog(null, "Số a phải khác 0");
                    txtA.requestFocus();
                    return;
                }

                float kq = -b / a;
                txtKQ.setText(Float.toString(kq));
            }
        });

        btnXoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtA.setText("");
                txtb.setText("");
                txtKQ.setText("");
            }
        });
        btnThoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    private boolean isNumeric(String str) {
        try {
            Float.parseFloat(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Bai1");
        frame.setContentPane(new Bai1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
