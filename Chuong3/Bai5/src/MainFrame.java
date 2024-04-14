import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.EventListener;

public class MainFrame {
    private JMenu mnuHeThong;
    private JMenuItem mnuDangNhap;
    private JMenuItem mnuDangXuat;
    private JMenuItem mnuThoat;
    private JMenu mnuQuanLy;
    private JMenu mnuHuongDan;
    private JPanel panel1;
    private JMenuItem mnuLoaiSach;
    private JMenuItem mnuSach;
    private JMenuItem mnuHoaDon;
    private JMenuItem mnuPhieuNhapSach;
    private JPanel pnContent;

    public String tenNV;

    public MainFrame() {
        this.mnuDangXuat.setEnabled(false);
        this.mnuHoaDon.setEnabled(false);
        this.mnuLoaiSach.setEnabled(false);
        this.mnuSach.setEnabled(false);
        this.mnuPhieuNhapSach.setEnabled(false);
        frmDangNhap frmDN = new frmDangNhap();
        frmLoaiSach frmLS = new frmLoaiSach();
        frmSach frmS = new frmSach();
        frmPhieuNhapSach frmPNS = new frmPhieuNhapSach();
        frmPNS.pack();
        frmDN.pack();
        frmLS.pack();
        frmS.pack();
        mnuDangNhap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmDN.setLocationRelativeTo(null);
                frmDN.setVisible(true);
                tenNV = frmDN.getStrTenNguoiDung();
                if(frmDN.getKetQuaDangNhap())
                {
                    mnuHoaDon.setEnabled(true);
                    mnuLoaiSach.setEnabled(true);
                    mnuPhieuNhapSach.setEnabled(true);
                    mnuSach.setEnabled(true);
                    mnuDangXuat.setEnabled(true);
                    mnuDangNhap.setEnabled(false);
                }
            }
        });
        mnuDangXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mnuHoaDon.setEnabled(false);
                mnuLoaiSach.setEnabled(false);
                mnuPhieuNhapSach.setEnabled(false);
                mnuSach.setEnabled(false);
                mnuDangXuat.setEnabled(false);
                mnuDangNhap.setEnabled(true);
            }
        });
        mnuLoaiSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmLS.setLocationRelativeTo(null);
                frmLS.setVisible(true);
            }
        });
        mnuSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmS.setLocationRelativeTo(null);
                frmS.setVisible(true);
            }
        });
        mnuHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmHoaDon frmHD = new frmHoaDon(tenNV);
                frmHD.pack();
                frmHD.setLocationRelativeTo(null);
                frmHD.setVisible(true);
            }
        });
        mnuPhieuNhapSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPNS.setLocationRelativeTo(null);
                frmPNS.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame();
        frame.setContentPane(new MainFrame().panel1);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.pack();
        frame.setLocationRelativeTo(null);
    }
}
