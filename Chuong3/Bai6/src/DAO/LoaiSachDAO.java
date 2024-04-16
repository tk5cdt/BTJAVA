package DAO;

import POJO.LoaiSach;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiSachDAO {
    public LoaiSachDAO() {
    }
    public static LoaiSachDAO instance = new LoaiSachDAO();
    public List<LoaiSach> getAll() throws SQLException {
        String sql = "SELECT * FROM View_LS_ID_With_RowNum";
        ResultSet rs = DBConnect.getInstance().executeQuery(sql);
        List<LoaiSach> loaiSaches = new ArrayList<>();
        while (rs.next()) {
            LoaiSach loaiSach = new LoaiSach(rs.getInt("LS_ID"), rs.getString("TEN"), rs.getString("MOTA"));
            loaiSaches.add(loaiSach);
        }
        return loaiSaches;
    }
    public int add(LoaiSach loaiSach) throws SQLException {
        String sql = "INSERT INTO LOAISACH(TEN, MOTA) VALUES ('" + loaiSach.getTen() + "', '" + loaiSach.getMoTa() + "')";
        return DBConnect.getInstance().executeUpdate(sql);
    }
    public int update(LoaiSach loaiSach) throws SQLException {
        String sql = "UPDATE LOAISACH SET TEN = '" + loaiSach.getTen() + "', MOTA = '" + loaiSach.getMoTa() + "' WHERE LS_ID = " + loaiSach.getId();
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public int delete(int id) throws SQLException {
        String sql = "UPDATE LOAISACH SET XOA = 1 WHERE LS_ID = " + id;
        return DBConnect.getInstance().executeUpdate(sql);
    }

    public LoaiSach getById(int id) throws SQLException {
        String sql = "SELECT * FROM LOAISACH WHERE LS_ID = " + id + " AND XOA = 0";
        ResultSet rs = DBConnect.getInstance().executeQuery(sql);
        if (rs.next()) {
            return new LoaiSach(rs.getInt("LS_ID"), rs.getString("TEN"), rs.getString("MOTA"));
        }
        return null;
    }

    public LoaiSach search(String ten) throws SQLException {
        String sql = "SELECT * FROM LOAISACH WHERE TEN LIKE N'%" + ten + "%' AND XOA = 0";
        ResultSet rs = DBConnect.getInstance().executeQuery(sql);
        LoaiSach loaiSach = null;
        if (rs.next()) {
            loaiSach = new LoaiSach(rs.getInt("LS_ID"), rs.getString("TEN"), rs.getString("MOTA"));
        }
        return loaiSach;
    }
    public int getRowNum(int id) throws SQLException {
        String sql = "select * from View_LS_ID_With_RowNum where LS_ID = "+id;
        ResultSet rs = DBConnect.getInstance().executeQuery(sql);
        rs.next();
        return rs.getInt(1);
    }

    public boolean isExist(String ten, int id) throws SQLException {
        String sql = "SELECT * FROM LOAISACH WHERE TEN = N'" + ten + "' AND LS_ID != " + id + " AND XOA = 0";
        ResultSet rs = DBConnect.getInstance().executeQuery(sql);
        return rs.next();
    }
}
