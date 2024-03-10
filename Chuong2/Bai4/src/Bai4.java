import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class Bai4 {
    private double total1 = 0.0;
    private  double total2 = 0.0;

    private String mathButton;

    private JPanel panel1;
    private JTextField textField1;
    private JButton btnOne;
    private JButton btnFour;
    private JButton btnSeven;
    private JButton btnZero;
    private JButton btnTwo;
    private JButton btnThree;
    private JButton btnDevide;
    private JButton btnSqurt;
    private JButton btnFive;
    private JButton btnSix;
    private JButton btnMul;
    private JButton btnPercent;
    private JButton btnEight;
    private JButton btnNine;
    private JButton btnMinus;
    private JButton btnInverse;
    private JButton btnEqual;
    private JButton btnPlus;
    private JButton btnClear;
    private JButton btnNegate;

    private void getOperator(String btn) {
        mathButton = btn.charAt(0) + "";
        total1 = total1 + Double.parseDouble(textField1.getText());
        textField1.setText("");
    }

    public Bai4() {
        textField1.setEditable(false);
        List<JButton> numList = List.of(btnZero, btnOne, btnTwo, btnThree, btnFour, btnFive, btnSix, btnSeven, btnEight, btnNine);
        for (JButton btn : numList) {
            btn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    textField1.setText(textField1.getText() + btn.getText());
                }
            });
        }
        btnNegate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (textField1.getText().length() > 0) {
                    if (textField1.getText().charAt(0) == '-') {
                        textField1.setText(textField1.getText().substring(1));
                    } else {
                        textField1.setText("-" + textField1.getText());
                    }
                }
            }
        });
        btnEqual.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                switch (mathButton + "") {
                    case "+":
                        total2 = total1 + Double.parseDouble(textField1.getText());
                        break;
                    case "-":
                        total2 = total1 - Double.parseDouble(textField1.getText());
                        break;
                    case "x":
                        total2 = total1 * Double.parseDouble(textField1.getText());
                        break;
                    case "/":
                        total2 = total1 / Double.parseDouble(textField1.getText());
                        break;
                }
                textField1.setText(Double.toString(total2));
                total1 = 0;
            }
        });
        btnClear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total2 = 0;
                textField1.setText("");
                mathButton = "";
            }
        });
        btnPlus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnPlus.getText());
            }
        });
        btnSqurt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        btnDevide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnDevide.getText());
            }
        });
        btnMul.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnMul.getText());
            }
        });
        btnMinus.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                getOperator(btnMinus.getText());
            }
        });
        btnPercent.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total2 = total1 * Double.parseDouble(textField1.getText()) / 100;
                textField1.setText(Double.toString(total2));
                total1 = 0;
            }
        });
        btnInverse.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total2 = 1 / Double.parseDouble(textField1.getText());
                textField1.setText(Double.toString(total2));
                total1 = 0;
            }
        });
        btnSqurt.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                total2 = Math.sqrt(Double.parseDouble(textField1.getText()));
                textField1.setText(Double.toString(total2));
                total1 = 0;
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Bai4");
        frame.setContentPane(new Bai4().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
    }
}
