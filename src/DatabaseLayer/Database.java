package DatabaseLayer;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {

    public static Connection getConnection() {
        try {
            String url = "jdbc:sqlite:OnlineshopDatabase";
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            throw new RuntimeException("Error connecting to database!", e);
        }
    }
}