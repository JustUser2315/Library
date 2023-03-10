package util;

import lombok.experimental.UtilityClass;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ResourceBundle;

@UtilityClass
public class ConnectionManager {
    private final String URL = "db.url";
    private final String USERNAME = "db.user";
    private final String PASSWORD = "db.password";

    static {
        loadDriver();
    }

    private static void loadDriver() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public Connection get() {
        ResourceBundle resourceBundle = ResourceBundle.getBundle("connection");
        try {
            return DriverManager.getConnection(
                    resourceBundle.getString(URL),
                    resourceBundle.getString(USERNAME),
                    resourceBundle.getString(PASSWORD)
            );
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}