package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HoaDonDAO {
    public HoaDonDAO() {
    }

    public static HoaDonDAO instance = new HoaDonDAO();
    public ResultSet getHoaDon() throws SQLException {
        String sql = "select * from HOADON where XOA = 0";
        return DBConnect.getInstance().executeQuery(sql);
    }
    public ResultSet getHoaDonTheoMa(String maHD) throws SQLException {
        String sql = "select TIEUDE, SOLUONG, GIABAN from SACH s join CHITIETHOADON ct on s.S_ID = ct.IDSACH and ct.IDHOADON ="+maHD;
        return DBConnect.getInstance().executeQuery(sql);
    }
    public int themHoaDon(String ngayLap, String tenNV, String tenKH, String ghiChu) throws SQLException {
        String sql = "insert into HOADON (NGAY, NHANVIEN, TENKHACHHANG, GHICHU, XOA) values('"+ngayLap+"', N'"+tenNV+"', N'"+tenKH+"', N'"+ghiChu+"', 0)";
        return DBConnect.getInstance().executeUpdate(sql);
    }
    public int suaHoaDon(String maHD, String ngayLap, String tenNV, String tenKH, String ghiChu) throws SQLException {
        String sql = "update HOADON set NGAY = '"+ngayLap+"', NHANVIEN = N'"+tenNV+"', TENKHACHHANG = N'"+tenKH+"', GHICHU = N'"+ghiChu+"' where HD_ID = '"+maHD+"'";
        return DBConnect.getInstance().executeUpdate(sql);
    }
    public int xoaHoaDon(String maHD) throws SQLException {
        String sql = "update HOADON set XOA = 1 where HD_ID = '"+maHD+"'";
        return DBConnect.getInstance().executeUpdate(sql);
    }
}
