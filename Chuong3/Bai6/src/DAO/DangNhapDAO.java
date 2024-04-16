package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class DangNhapDAO {
    public DangNhapDAO() {
    }

    public static DangNhapDAO instance = new DangNhapDAO();
    public ResultSet login(String username, String password) throws SQLException {
        String sql = "select * from NGUOIDUNG where "
                + "TENDANGNHAP = N'"+ username +"'"
                +"and MATKHAU = N'"+ password +"'";
        return DBConnect.getInstance().executeQuery(sql);
    }
}
