import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBconnect {

    private static DBconnect instance;

    public static DBconnect getInstance() {
        if(instance == null) {
            instance = new DBconnect();
        }
        return instance;
    }

    Connection con;
    public DBconnect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String connectionUrl = "jdbc:sqlserver://DESKTOP-S9AMOBJ:1433;databaseName=QL_SANPHAM;;encrypt=true;trustServerCertificate=true;";
            String connectionUrl = "jdbc:sqlserver://TON:1433;databaseName=QL_SANPHAM;;encrypt=true;trustServerCertificate=true;";
            String username = "sa";
            String password = "123";
            con = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public DBconnect(String server, String port, String dbName){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://"+server+":"+port+";databaseName="+dbName+";;encrypt=true;trustServerCertificate=true;";
            String username = "sa";
            String password = "123";
            con = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Connect successfully");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection getConnection(){
        return con;
    }

    public void closeConnection(){
        try {
            con.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public ResultSet executeQuery(String sql){
        try {
            return con.createStatement().executeQuery(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void executeUpdate(String sql){
        try {
            con.createStatement().executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}