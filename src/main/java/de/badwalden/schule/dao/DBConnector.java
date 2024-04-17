package de.badwalden.schule.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class DBConnector {

    private static Connection connect() {
        Dotenv dotenv = Dotenv.configure().load();
        String jdbcUrl = dotenv.get("DB_URL");
        String username = dotenv.get("USER_NAME");
        String password = dotenv.get("PW");

        try {
            return DriverManager.getConnection(jdbcUrl, username, password);
        } catch (SQLException e) {
            System.out.println("Failed to establish connection");
            e.printStackTrace();
            return null;
        }
    }

    private static void close(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection");
            e.printStackTrace();
        }

    }

    public static void executeQuery(String sql) {
        Connection connection = connect();
        if (connection != null) {
            try (Statement stmt = connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                while (rs.next()) {
                    // Assuming you're fetching a string from the first column in your result set
                    System.out.println(rs.getString(1));
                }

            } catch (SQLException e) {
                System.out.println("Error executing query");
                e.printStackTrace();
            }
        }
    }
}

