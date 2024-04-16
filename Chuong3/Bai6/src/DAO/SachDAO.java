package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SachDAO {
    public SachDAO() {
    }
    public static SachDAO instance = new SachDAO();
    public ResultSet getSach() throws SQLException {
        String sql = "select S_ID,TIEUDE,TACGIA,NAMXUATBAN,ls.TEN,s.MOTA from SACH s join LOAISACH ls on s.TheLoai = ls.LS_ID and s.XOA = 0";
        return DBConnect.getInstance().executeQuery(sql);
    }
    public ResultSet getSachTheoMa(String maSach) throws SQLException {
        String sql = "select S_ID,TIEUDE,TACGIA,NAMXUATBAN,ls.TEN,s.MOTA from SACH s join LOAISACH ls on s.TheLoai = ls.LS_ID where S_ID ="+maSach + " and s.XOA = 0";
        return DBConnect.getInstance().executeQuery(sql);
    }

    public int themSach(String tieuDe, String tacGia, String namXuatBan, String theLoai, String moTa) throws SQLException {
        String sql = "insert into SACH (TIEUDE, TACGIA, NAMXUATBAN, THELOAI, MOTA, XOA) values(N'"+tieuDe+"', N'"+tacGia+"', '"+namXuatBan+"', '"+theLoai+"', N'"+moTa+"', 0)";
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public int suaSach(String maSach, String tieuDe, String tacGia, String namXuatBan, String theLoai, String moTa) throws SQLException {
        String sql = "update SACH set TIEUDE = N'"+tieuDe+"', TACGIA = N'"+tacGia+"', NAMXUATBAN = '"+namXuatBan+"', THELOAI = "+theLoai+", MOTA = N'"+moTa+"' where S_ID = '"+maSach+"'";
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public int xoaSach(String maSach) throws SQLException {
        String sql = "update SACH set XOA = 1 where S_ID = '"+maSach+"'";
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public ResultSet timKiemSach(String tieuDe) throws SQLException {
        String sql = "select S_ID,TIEUDE,TACGIA,NAMXUATBAN,ls.TEN,s.MOTA from SACH s join LOAISACH ls on s.TheLoai = ls.LS_ID where TIEUDE like N'"+tieuDe+"' and s.XOA = 0";
        return DBConnect.getInstance().executeQuery(sql);
    }

    public ResultSet timKiemSach(String tieude, int id) throws SQLException {
        String sql = "select S_ID,TIEUDE,TACGIA,NAMXUATBAN,ls.TEN,s.MOTA from SACH s join LOAISACH ls on s.TheLoai = ls.LS_ID where TIEUDE like N'"+tieude+"' and S_ID != "+id+" and s.XOA = 0";
        return DBConnect.getInstance().executeQuery(sql);
    }

}
