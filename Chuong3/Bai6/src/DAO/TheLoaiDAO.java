package DAO;

import POJO.TheLoai;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TheLoaiDAO {
    public List<TheLoai> getAll() throws SQLException {
        String sql = "SELECT * FROM TheLoai";
        ResultSet rs = DBConnect.getInstance().executeQuery(sql);
        List<TheLoai> theLoais = new ArrayList<>();
        while (rs.next()) {
            TheLoai theLoai = new TheLoai(rs.getInt("id"), rs.getString("ten"));
            theLoais.add(theLoai);
        }
        return theLoais;
    }
}
