import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnect {

    private static DBConnect instance;

    public static DBConnect getInstance() {
        if(instance == null) {
            instance = new DBConnect();
        }
        return instance;
    }

    public void setInstance(String server, String port, String dbName, String username, String password) {
        instance = new DBConnect(server, port, dbName, username, password);
    }

    Connection con;
    public DBConnect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://DESKTOP-KFOVQS4:1433;databaseName=QL_KHACHHANG;encrypt=true;trustServerCertificate=true;";
            String username = "sa";
            String password = "123";
            con = DriverManager.getConnection(connectionUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public DBConnect(String server, String port, String dbName, String username, String password){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://"+server+":"+port+";databaseName="+dbName+";;encrypt=true;trustServerCertificate=true;";
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
