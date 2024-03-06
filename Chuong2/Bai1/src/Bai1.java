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
                float a = Float.parseFloat(txtA.getText());
                float b = Float.parseFloat(txtb.getText());
                float kq = -b/a;
                txtKQ.setText(kq + "");
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
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Bai1");
        frame.setContentPane(new Bai1().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
