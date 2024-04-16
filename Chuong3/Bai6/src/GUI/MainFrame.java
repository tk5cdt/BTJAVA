package GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
    public String idNV;

    public MainFrame() {
        this.mnuDangXuat.setEnabled(false);
        this.mnuHoaDon.setEnabled(false);
        this.mnuLoaiSach.setEnabled(false);
        this.mnuSach.setEnabled(false);
        this.mnuPhieuNhapSach.setEnabled(false);
        frmDangNhap frmDN = new frmDangNhap();
        frmLoaiSach frmLS = new frmLoaiSach();

        frmDN.pack();
        frmLS.pack();
        mnuDangNhap.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                frmDN.setLocationRelativeTo(null);
                frmDN.setVisible(true);
                tenNV = frmDN.getStrTenNguoiDung();
                idNV = frmDN.getIdNguoiDung();
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
                frmSach frmS = new frmSach();
                frmS.pack();
                frmS.setLocationRelativeTo(null);
                frmS.setVisible(true);
            }
        });
        mnuHoaDon.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmHoaDon frmHD = new frmHoaDon(tenNV, idNV);
                frmHD.pack();
                frmHD.setLocationRelativeTo(null);
                frmHD.setVisible(true);
            }
        });
        mnuPhieuNhapSach.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frmPhieuNhapSach frmPNS = new frmPhieuNhapSach(tenNV, idNV);
                frmPNS.pack();
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