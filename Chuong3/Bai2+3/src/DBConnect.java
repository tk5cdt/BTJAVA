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

    Connection con;
    public DBConnect(){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //String connectionUrl = "jdbc:sqlserver://DESKTOP-S9AMOBJ:1433;databaseName=QL_DIACDDVD;encrypt=true;trustServerCertificate=true;";
            String connectionUrl = "jdbc:sqlserver://TON:1433;databaseName=QL_DIACDDVD;encrypt=true;trustServerCertificate=true;";
            String username = "sa";
            String password = "123";
            con = DriverManager.getConnection(connectionUrl, username, password);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public DBConnect(String server, String port, String dbName){
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionUrl = "jdbc:sqlserver://"+server+":"+port+";databaseName="+dbName+";;encrypt=true;trustServerCertificate=true;";

            String username = "sa";
            String password = "123";
            con = DriverManager.getConnection(connectionUrl, username, password);
            System.out.println("Hello");
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