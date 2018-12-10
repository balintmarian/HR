import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getConnection() throws SQLException{
        String URL="jdbc:hsqldb:hsql://localhost/employees";
        Connection con = DriverManager.getConnection(URL,"SA","");
        return con;
    }
}
