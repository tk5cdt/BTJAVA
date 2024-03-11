import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Bai2 {
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JButton btn_Giai;
    private JButton btn_Xoa;
    private JButton btn_Thoat;
    private JPanel panel1;

    public Bai2() {

        btn_Giai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Get coefficients
                    double a = Double.parseDouble(textField1.getText());
                    double b = Double.parseDouble(textField2.getText());
                    double c = Double.parseDouble(textField3.getText());

                    // Calculate discriminant
                    double delta = b * b - 4 * a * c;

                    // Determine and display solution
                    if (delta > 0) {
                        double x1 = ((-b + Math.sqrt(delta)) / (2 * a)) ;
                        double x2 = ((-b - Math.sqrt(delta)) / (2 * a)) ;
                        textField4.setText(" x1 = " + x1  + ", x2 = " + x2);
                    } else if (delta == 0) {
                        double x = -b / (2 * a);
                        textField4.setText("Phương trình có nghiệm kép: x1 = x2 = " + x);
                    } else {
                        textField4.setText("Phương trình vô nghiệm");
                    }
                } catch (NumberFormatException ex) {
                    // Handle invalid input
                    textField4.setText("Lỗi: Nhập dữ liệu không hợp lệ!");
                }
            }
        });

        btn_Xoa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField1.setText("");
                textField2.setText("");
                textField3.setText("");
                textField4.setText("");
            }
        });
        btn_Thoat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Bai2");
        frame.setContentPane(new Bai2().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }
}
