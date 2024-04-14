import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        new DBConnect();
        try{
            String sql = "Select makh, tenkh, namsinh from KHACHHANG";
            ResultSet rs = DBConnect.getInstance().executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("MAKH") + " " + rs.getString("TENKH") + " " + rs.getString("NAMSINH"));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}