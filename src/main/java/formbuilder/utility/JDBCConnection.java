package formbuilder.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCConnection {
 
     public static Connection connect() throws ClassNotFoundException, SQLException {
            Class.forName("org.postgresql.Driver");
            return DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres","postgres","admin");
    }

    private JDBCConnection() {}

}