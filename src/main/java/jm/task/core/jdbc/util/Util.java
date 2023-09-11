package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    public static final String DB_Driver = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/users";
    public static final String Username = "root";
    public static final String Password = "root";

    public static Connection getConnection() {
        Connection connection;
        try {
            Class.forName(DB_Driver);
            connection = DriverManager.getConnection(DB_URL, Username, Password);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return connection;
    }
}



