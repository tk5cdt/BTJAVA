import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.event.*;
import java.io.*;
import java.util.Enumeration;
import java.util.StringTokenizer;

public class QuanLyNhanVien extends JFrame {
    private JTree treeDpt;
    private JTextField txtDeptCode;
    private JTextField txtDeptName;
    private JTextField txtEmpCode;
    private JButton btnNewDPT;
    private JButton btnRemoveDPT;
    private JButton btnSaveDPT;
    private JTextField txtEmpName;
    private JTextField txtSalary;
    private JButton btnNewEMP;
    private JButton btnRemoveEMP;
    private JButton btnSaveEMP;
    private JPanel panel1;
    private JButton btnSaveToF;

    String fileName = "C:\\Users\\vothi\\Documents\\HK6\\Java\\Java_Trees\\Bai9_Chuong2\\src\\data.txt";
    DefaultMutableTreeNode root = null;
    DefaultMutableTreeNode curDepNode = null;
    DefaultMutableTreeNode curEmpNode = null;

    boolean addNewDep = false;
    boolean addNewEmp = false;

    public QuanLyNhanVien() {
        root = (DefaultMutableTreeNode) (this.treeDpt.getModel().getRoot());
        loadData();
        TreePath treePath = new TreePath(root);
        treeDpt.expandPath(treePath);

        treeDpt.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                treeDpt.cancelEditing();

                TreePath path = treeDpt.getSelectionPath();
                if (path == null) return;
                DefaultMutableTreeNode selectedNode = null;
                selectedNode = (DefaultMutableTreeNode) (path.getLastPathComponent());
                Object selectedObj = selectedNode.getUserObject();
                if (selectedNode == root)
                    curDepNode = curEmpNode = null;
                else if (selectedObj instanceof Department) {
                    curDepNode = selectedNode;
                    curEmpNode = null;
                } else if (selectedObj instanceof Employee) {
                    curEmpNode = selectedNode;
                    curDepNode = (DefaultMutableTreeNode) (selectedNode.getParent());
                }
                viewDeptAndEmp();
            }
        });

        btnNewDPT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewDep = true;
                txtDeptCode.setText("");
                txtDeptName.setText("");
                txtEmpCode.setText("");
                txtEmpName.setText("");
                txtSalary.setText("");
                txtDeptCode.setEnabled(true);
                txtDeptCode.requestFocus();
            }
        });

        btnRemoveDPT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (curDepNode.getChildCount() > 0) {
                    String ms = "Remove all employees before deleting a deparment.";
                    JOptionPane.showMessageDialog(null, ms);
                } else {
                    int respone = JOptionPane.showConfirmDialog(null, "Delete this deparment - Oke ?");
                    if (respone == JOptionPane.OK_OPTION) {
                        root.remove(curDepNode);
                        treeDpt.updateUI();
                    }
                }
            }
        });
        btnSaveDPT.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = txtDeptCode.getText().trim().toUpperCase();
                txtDeptCode.setText(code);
                String name = txtDeptName.getText().trim();
                txtDeptName.setText(name);
                if (!validDepDetails()) return;
                if (addNewDep == true) {
                    Department newDept = new Department(code, name);
                    root.add(new DefaultMutableTreeNode(newDept));
                } else {
                    ((Department) curDepNode.getUserObject()).setDepCode(name);
                }
                treeDpt.updateUI();
                addNewDep = false;
            }
        });
        btnNewEMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewEmp = true;
                txtEmpName.setText("");
                txtEmpCode.setText("");
                txtSalary.setText("");
                txtEmpCode.setEditable(true);
                txtEmpCode.requestFocus();
            }
        });
        btnRemoveEMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (curEmpNode != null) {
                    int respone = JOptionPane.showConfirmDialog(null, "Delete this employee - OK ?");
                    if (respone == JOptionPane.OK_OPTION) {
                        curDepNode.remove(curEmpNode);
                        treeDpt.updateUI();
                    }
                }
            }
        });
        btnSaveEMP.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String code = txtEmpCode.getText().trim().toUpperCase();
                txtEmpCode.setText(code);
                String name = txtEmpName.getText().trim();
                txtEmpName.setText(name);
                String salary = txtSalary.getText().trim();
                if (!isInteger(salary)) {
                    JOptionPane.showMessageDialog(null, "Lương không được phép là chữ");
                    txtSalary.requestFocus();
                    return;
                }

                // Tiếp tục xử lý khi chuỗi nhập vào là số nguyên
                int salaryValue = Integer.parseInt(salary);
                if (salaryValue < 0) {
                    JOptionPane.showMessageDialog(null, "Lương không được âm");
                    txtSalary.requestFocus();
                    return;
                }
                txtSalary.setText(salary);

                int sal = Integer.parseInt(salary);

                if (addNewEmp == true) {
                    Employee newEmp = new Employee(code, name, sal);
                    curDepNode.add(new DefaultMutableTreeNode(newEmp));
                } else {
                    Employee emp = (Employee) (curEmpNode.getUserObject());
                    emp.setEmpName(name);
                    emp.setEmpSalary(sal);
                }
                treeDpt.updateUI();
                addNewEmp = false;
            }
        });
        btnSaveToF.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (root.getChildCount() == 0) {
                    return;
                }
                String S;
                try {
                    FileWriter f = new FileWriter(fileName);
                    PrintWriter pf = new PrintWriter(f);
                    Enumeration depts = root.children();
                    while (depts.hasMoreElements()) {
                        DefaultMutableTreeNode depNode = (DefaultMutableTreeNode) depts.nextElement();
                        Department dept = (Department) (depNode.getUserObject());
                        S = dept.getDepCode() + "-" + dept.getDepName() + ":";
                        pf.println(S);
                        Enumeration emps = depNode.children();
                        while (emps.hasMoreElements()) {
                            DefaultMutableTreeNode empNode = (DefaultMutableTreeNode) emps.nextElement();
                            Employee emp = (Employee) (empNode.getUserObject());
                            S = emp.getEmpCode() + "," + emp.getEmpName() + "," + emp.getEmpSalary();
                            pf.println(S);
                        }
                    }
                    pf.close();
                    f.close();
                    JOptionPane.showMessageDialog(null, "Data saved to the file" + fileName);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        });
    }

    private boolean validDepDetails() {
        return true;
    }
    public static boolean isInteger(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("QuanLyNhanVien");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(new QuanLyNhanVien().panel1);
        frame.pack();
        frame.setVisible(true);
    }

    private void setUpTree() {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Deparment");
        DefaultTreeModel treeModel = new DefaultTreeModel(root);
        treeDpt = new JTree(treeModel);
    }

    private void loadData() {
        String S = "";
        StringTokenizer stk;
        try {
            FileReader f = new FileReader(fileName);
            BufferedReader bf = new BufferedReader(f);
            while ((S = bf.readLine()) != null) {
                S = S.trim();
                boolean isDept = (S.charAt(S.length() - 1) == ':');
                stk = new StringTokenizer(S, "-:,");
                String Code = stk.nextToken().trim();
                String Name = stk.nextToken().trim();
                if (isDept) {
                    curDepNode = new DefaultMutableTreeNode(new Department(Code, Name));
                    root.add(curDepNode);
                } else {
                    int empSalary = Integer.parseInt(stk.nextToken().trim());
                    curEmpNode = new DefaultMutableTreeNode(new Employee(Code, Name, empSalary));
                    curDepNode.add(curEmpNode);
                }
            }
            bf.close();
            f.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    private void createUIComponents() {
        setUpTree();
    }

    private void viewDeptAndEmp() {
        Department curDep = null;
        Employee curEmp = null;
        if (curDepNode != null) {
            curDep = (Department) (curDepNode.getUserObject());
        }
        if (curEmpNode != null) {
            curEmp = (Employee) (curEmpNode.getUserObject());
        }
        this.txtDeptCode.setText(curDep != null ? curDep.getDepCode() : "");
        this.txtDeptName.setText(curDep != null ? curDep.getDepName() : "");
        this.txtEmpCode.setText(curEmp != null ? curEmp.getEmpCode() : "");
        this.txtEmpName.setText(curEmp != null ? curEmp.getEmpName() : "");
        this.txtSalary.setText("" + (curEmp != null ? curEmp.getEmpSalary() : ""));
    }


}
