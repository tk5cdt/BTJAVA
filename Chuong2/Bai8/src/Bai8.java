import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Bai8 {
    String fileName = "src/Bai8.txt";
    String[] columnNames = {"Code", "Name", "Salary"};
    Object[][] data;

    boolean addNew = false;
    boolean changed = false;

    private JPanel panelMain;
    private JScrollPane jscltable;
    private JTextField txtCode;
    private JTextField txtName;
    private JTextField txtSalary;
    private JPanel jpanelText;
    private JButton btnNew;
    private JButton btnRemove;
    private JButton btnSaveFile;
    private JButton btnSaveTable;
    private JButton btnExit;
    private JTable table1;

    public Bai8() {
        loadData();
        table1 = new JTable(data, columnNames);
        jscltable.setViewportView(table1);
        btnNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNew = true;
                txtCode.setText("");
                txtCode.setEditable(true);
                txtName.setText("");
                txtSalary.setText("");
                txtCode.requestFocus();
            }
        });
        btnSaveFile.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Save data to file
                try {
                    File file = new File(fileName);
                    FileWriter fw = new FileWriter(file);
                    BufferedWriter bw = new BufferedWriter(fw);
                    for (int i = 0; i < data.length; i++) {
                        for (int j = 0; j < data[i].length; j++) {
                            bw.write(data[i][j].toString());
                            if (j < data[i].length - 1) {
                                bw.write(",");
                            }
                        }
                        bw.newLine();
                    }
                    bw.close();
                    fw.close();
                    changed = false;
                    addNew = false;
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
        btnSaveTable.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addNew) {
                    // Add new row
                    Object[] newRow = {txtCode.getText(), txtName.getText(), txtSalary.getText()};
                    Object[][] newData = new Object[data.length + 1][3];
                    for (int i = 0; i < data.length; i++) {
                        for (int j = 0; j < data[i].length; j++) {
                            newData[i][j] = data[i][j];
                        }
                    }
                    newData[data.length] = newRow;
                    data = newData;
                    addNew = false;
                    changed = true;
                } else {
                    // Update row
                    int row = table1.getSelectedRow();
                    if (row == -1) {
                        return;
                    }
                    data[row][0] = txtCode.getText();
                    data[row][1] = txtName.getText();
                    data[row][2] = txtSalary.getText();
                    changed = true;
                }
                table1 = new JTable(data, columnNames);
                jscltable.setViewportView(table1);
                addNew = false;
            }
        });
        btnRemove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNew = false;
                int row = table1.getSelectedRow();
                if (row == -1) {
                    return;
                }
                Object[][] newData = new Object[data.length - 1][3];
                for (int i = 0; i < row; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        newData[i][j] = data[i][j];
                    }
                }
                for (int i = row + 1; i < data.length; i++) {
                    for (int j = 0; j < data[i].length; j++) {
                        newData[i - 1][j] = data[i][j];
                    }
                }
                data = newData;
                table1 = new JTable(data, columnNames);
                jscltable.setViewportView(table1);
                changed = true;
            }
        });
        btnExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (changed) {
                    int result = JOptionPane.showConfirmDialog(null, "Do you want to save data to file?", "Save data", JOptionPane.YES_NO_CANCEL_OPTION);
                    if (result == JOptionPane.YES_OPTION) {
                        btnSaveFile.doClick();
                    } else if (result == JOptionPane.CANCEL_OPTION) {
                        return;
                    }
                }
                System.exit(0);
            }
        });
    }

    private void loadData() {
        // Load data from file
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader br = new BufferedReader(fr);
            String line = "";
            List<String> list = new ArrayList<String>();
            while ((line = br.readLine()) != null) {
                list.add(line);
            }
            int n = list.get(0).split(" ").length;
            data = new Object[list.size()][n];
            for (int i = 0; i < list.size(); i++) {
                String[] str = list.get(i).split(",");
                data[i] = str;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("Bai8");
        frame.setContentPane(new Bai8().panelMain);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        //set frame to middle of screen
        frame.setLocationRelativeTo(null);
    }
}