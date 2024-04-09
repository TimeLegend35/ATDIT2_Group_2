package de.badwalden.schule.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



public class DBConnection {

    public static void main(String[]args ) throws SQLException {
        String jdbcUrl = System.getenv("db_url");
        String username = System.getenv("user_name");
        String password = System.getenv("pw");

        Connection connection = null;

        try {
            // Establish the connection
            connection = DriverManager.getConnection(jdbcUrl, username, password);

            // Connection successful! You can now use 'connection' to execute SQL queries.
            System.out.println("Connection established");
            // Example: Execute SQL queries here
            // ...
        }  finally {
            // Close the connection in the finally block to ensure it's always closed
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Failed to close connection");
                    e.printStackTrace();
                }
            }
        }
    }
}
