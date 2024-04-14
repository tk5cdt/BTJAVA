import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectDB {
    public Connection getConnection()
    {
        String  strServer = "Ton";
        String strDatabase = "QL_NhaSach";
        Connection connection = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String connectionURL = "jdbc:sqlserver://" + strServer
                    + ":1433;databaseName=" + strDatabase
                    + ";user=sa;password=123"
                    + ";encrypt=true;trustServerCertificate=true;";
            connection = DriverManager.getConnection(connectionURL);
        }
        catch (Exception e)
        {
            System.out.print(e);
        }
        return connection;
    }
}
