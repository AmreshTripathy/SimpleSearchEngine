package org.example;
/*
 * @Amresh Tripathy
 */

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    static Connection connection = null;

    public static Connection getConnection() {
        if (connection != null) {
            return connection;
        }

        String user = "root";
        String pwd = "3838";
        String db = "simple_search_engine_java";

        return getConnection(user, pwd, db);
    }

    private static Connection getConnection(String user, String pwd, String db) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:4466/" + db + "?user=" + user + "&password=" + pwd);

        } catch (SQLException | ClassNotFoundException exception) {
            exception.printStackTrace();
        }

        return connection;
    }
}
