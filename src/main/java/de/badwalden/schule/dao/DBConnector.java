package de.badwalden.schule.dao;

import de.badwalden.schule.model.Student;
import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class DBConnector {

    private Connection connection;
    private static DBConnector instance;

    private DBConnector() {
        this.connection = connect();
    }

    public static synchronized DBConnector getInstance() {
        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    public Connection connect() {
        Dotenv dotenv = Dotenv.configure().load();
        String large_url = dotenv.get("CONNECTION_URL");

        try {
            return DriverManager.getConnection(large_url);
        } catch (SQLException e) {
            System.out.println("Failed to establish connection. 10 sec Timeout");
            try {
                // Pause execution for 10 seconds (10,000 milliseconds)
                Thread.sleep(10000);
            } catch (InterruptedException e2) {
                // This block is executed if the sleep is interrupted
                System.err.println("Sleep was interrupted");
            }
            e.printStackTrace();
            this.connect();
            return null;
        }
    }

    private void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            System.out.println("Failed to close connection");
            e.printStackTrace();
        }

    }

    public List<Object[]> executeQuery(String sql) {
        List<Object[]> results = new ArrayList<>();

        if (this.connection != null) {
            try (Statement stmt = this.connection.createStatement();
                 ResultSet rs = stmt.executeQuery(sql)) {

                int columnCount = rs.getMetaData().getColumnCount();

                while (rs.next()) {
                    Object[] row = new Object[columnCount];
                    for (int i = 0; i < columnCount; i++) {
                        row[i] = rs.getObject(i + 1); // ResultSet uses 1-based indexing for columns
                    }
                    results.add(row);
                }

            } catch (SQLException e) {
                System.out.println("Error executing query");
                e.printStackTrace();
               }
        }
        return results;
    }

    /**
     * Executes an update SQL statement.
     *
     * @param  sql   the SQL statement to be executed
     * @return      the number of rows affected by the update
     */
    public int executeUpdate(String sql) {
        if (this.connection != null) {
            try (Statement stmt = this.connection.createStatement()) {
                return stmt.executeUpdate(sql);
            } catch (SQLException e) {
                System.out.println("Error executing update");
                e.printStackTrace();
            }
        }
        return 0;
    }


}

