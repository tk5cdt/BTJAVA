package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuNhapDAO {
    public PhieuNhapDAO() {
    }
    public static PhieuNhapDAO instance = new PhieuNhapDAO();
    public ResultSet getPhieuNhap() throws SQLException {
        String sql = "select * from PHIEUNHAPSACH where XOA = 0";
        return DBConnect.getInstance().executeQuery(sql);
    }

    public ResultSet getPhieuNhapTheoMa(String maPNS) throws SQLException {
        String sql = "select TIEUDE, SOLUONG, DONGIA from SACH s join CHITIETPHIEUNHAPSACH ct on s.S_ID = ct.IDSACH and ct.ID_PNS ="+maPNS;
        return DBConnect.getInstance().executeQuery(sql);
    }

    public int themPhieuNhap(String ngayLap, String tenNV, String ghiChu) throws SQLException {
        String sql = "insert into PHIEUNHAPSACH (NGAY, NHANVIEN, GHICHU, XOA) values('"+ngayLap+"', N'"+tenNV+"', N'"+ghiChu+"', 0)";
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public int suaPhieuNhap(String maPNS, String ngayLap, String tenNV, String ghiChu) throws SQLException {
        String sql = "update PHIEUNHAPSACH set NGAY = '"+ngayLap+"', NHANVIEN = N'"+tenNV+"', GHICHU = N'"+ghiChu+"' where PNS_ID = '"+maPNS+"'";
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public int xoaPhieuNhap(String maPNS) throws SQLException {
        String sql = "update PHIEUNHAPSACH set XOA = 1 where PNS_ID = '"+maPNS+"'";
        return DBConnect.getInstance().executeUpdate(sql);
    }
}
