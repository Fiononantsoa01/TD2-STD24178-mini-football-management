package Player;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private final String jdbcUrl;
    private final String username;
    private final String password;

    public DBConnection() {
        jdbcUrl =System.getenv("JDBC_URL");
        username =System.getenv("USERNAME");
        password =System.getenv("PASSWORD");
        System.out.println("URL = [" + System.getenv("JDBC_URL") + "]");
        System.out.println("USERNAME = [" + System.getenv("USERNAME") + "]");
        System.out.println("PASSWORD = [" + System.getenv("PASSWORD") + "]");


        if (jdbcUrl == null || username == null || password == null) {
            throw new RuntimeException("you have to set the environment variables in the JDBC_URL and USERNAME and PASSWORD");
        }
    }
    public Connection getDBConnection() {
        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            throw new RuntimeException("connection to postgresql failed", e);
        }
    }
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();

            } catch (SQLException e) {
                throw new RuntimeException("Error while closing the database connection", e);
            }
        }
    }
}
