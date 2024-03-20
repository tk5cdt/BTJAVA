import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class Bai7 {
    private JTree tree1;
    private JTable table1;
    private JTextField txtMaKH;
    private JTextField txtTenKH;
    private JTextField txtSDT;
    private JTextField txtEmail;
    private JButton btnSave;
    private JButton btnDel;
    private JPanel pn1;
    private JScrollPane JScroll;

    Vector Vheader;
    Vector VnDung = new Vector();
    Vector Vdong = new Vector();

    DefaultTableModel dt = new DefaultTableModel();

    DefaultMutableTreeNode congTy = new DefaultMutableTreeNode("Công ty ABC");
    DefaultMutableTreeNode khachHangVip = new DefaultMutableTreeNode("Khách hàng VIP");
    DefaultMutableTreeNode khachHangTiemNang = new DefaultMutableTreeNode("Khách hàng tiềm năng");
    DefaultMutableTreeNode khachHangKhoTinh = new DefaultMutableTreeNode("Khách hàng khó tính");
    List<KhachHang> KH = new ArrayList<KhachHang>();
    public void load()
    {
        congTy.add(khachHangVip);
        congTy.add(khachHangTiemNang);
        congTy.add(khachHangKhoTinh);

        DefaultTreeModel model;

        model = (DefaultTreeModel)tree1.getModel();
        model.setRoot(congTy);
        tree1.setModel(model);
        for(int i = 0;i<tree1.getRowCount();i++)
        {
            tree1.expandRow(i);
        }

    }
    public Bai7() {
        // tree

        load();


        // table
        tieuDe();
        dt.setDataVector(Vdong,Vheader);
        table1.setModel(dt);
        JScroll.setViewportView(table1);
        btnSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //table
                KhachHang kh = new KhachHang();
                kh.setMaKH(txtMaKH.getText());
                kh.setTenKH(txtTenKH.getText());
                kh.setSDT(txtSDT.getText());
                kh.setEmail(txtEmail.getText());
                Nhap(kh);
                dt.addRow(Vdong);
                KH.add(kh);
                table1.setModel(dt);
                khachHangVip.removeAllChildren();
                // tree
                for(KhachHang khs:KH)
                {
                    DefaultMutableTreeNode khtree = new DefaultMutableTreeNode(khs.getTenKH());


                    khachHangVip.add(khtree);
                }

                load();
                txtMaKH.setText("");
                txtSDT.setText("");
                txtTenKH.setText("");
                txtEmail.setText("");
            }

        });
        btnDel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree1.getSelectionPath().getLastPathComponent();
                DefaultTreeModel model = (DefaultTreeModel) tree1.getModel();
                if(selectNode!= tree1.getModel().getRoot())

                    model.removeNodeFromParent(selectNode);
            }
        });
        tree1.addTreeSelectionListener(new TreeSelectionListener() {
            @Override
            public void valueChanged(TreeSelectionEvent e) {
                DefaultMutableTreeNode selectNode = (DefaultMutableTreeNode) tree1.getLastSelectedPathComponent();
                if(selectNode!=null)
                {
                    int n = khachHangVip.getIndex(selectNode);
                    KhachHang kh = KH.get(n);
                    txtMaKH.setText(kh.getMaKH());
                    txtTenKH.setText(kh.getTenKH());
                    txtSDT.setText(kh.getSDT());
                    txtEmail.setText(kh.getEmail());
                }
            }
        });
    }


    public void tieuDe()
    {
        Vheader = new Vector<>();
        Vheader.add("Mã khách hàng");
        Vheader.add("Tên khách hàng");
        Vheader.add("Số điện thoại");
        Vheader.add("Email");
    }

    void Nhap(KhachHang kh)
    {
        Vdong = new Vector();
        Vdong.add(kh.getMaKH());
        Vdong.add(kh.getTenKH());
        Vdong.add(kh.getSDT());
        Vdong.add(kh.getEmail());
    }

    public static void main(String[] args) {

        JFrame frame = new JFrame();
        frame.setContentPane(new Bai7().pn1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
