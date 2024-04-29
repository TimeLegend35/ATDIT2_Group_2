package de.badwalden.schule.dao;

import de.badwalden.schule.model.Student;
import de.badwalden.schule.ui.helper.DialogHelper;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.control.Alert;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
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
            if (large_url != null) {
                return DriverManager.getConnection(large_url);
            } else {
                throw new SQLException("CONNECTION_URL is null");
            }
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to establish connection. 10 sec Timeout", e);
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Database Connection Error", "Failed to establish a connection. Trying again in 15 seconds. If the Error persists, please contact the Administrator.");

            try {
                // Pause execution for 10 seconds
                Thread.sleep(10000);
            } catch (InterruptedException e2) {
                // This block is executed if the sleep is interrupted
                logger.log(Level.SEVERE, "Sleep was interrupted", e2);
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Database Connection Error", "Sleep was interrupted. If the Error persists, please contact the Administrator.");
            }

            this.close();
            this.connect();

            return null;
        }
    }

    private void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to close connection", e);
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Database Connection Error", "Failed to close the connection. If the Error persists, please contact the Administrator.");
        }
    }

    public List<Object[]> executeQuery(String sql, Object[] params) {
        List<Object[]> results = new ArrayList<>();

        if (this.connection != null) {
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]); // Set parameters for the prepared statement
                }
                try (ResultSet rs = pstmt.executeQuery()) {
                    int columnCount = rs.getMetaData().getColumnCount();
                    while (rs.next()) {
                        Object[] row = new Object[columnCount];
                        for (int i = 0; i < columnCount; i++) {
                            row[i] = rs.getObject(i + 1);
                        }
                        results.add(row);
                    }
                }
            } catch (SQLException e) {
                logger.log(Level.SEVERE, "Error executing query with prepared statement", e);
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Database Connection Error", "Error executing data query with prepared statement. If the Error persists, please contact the Administrator.");
            }
        }
        return results;
    }


    /**
     * Executes an update SQL statement.
     *
     * @param sql the SQL statement to be executed
     * @return the number of rows affected by the update
     */
    public int executeUpdate(String sql, Object[] params) {
        if (this.connection != null) {
            try (PreparedStatement pstmt = this.connection.prepareStatement(sql)) {
                for (int i = 0; i < params.length; i++) {
                    pstmt.setObject(i + 1, params[i]);
                }
                return pstmt.executeUpdate();
            } catch (SQLException e) {
                System.out.println("Error executing update with prepared statement");
                logger.log(Level.SEVERE, "Error executing update with prepared statement", e);
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, "Database Connection Error", "Error executing update with prepared statement. If the Error persists, please contact the Administrator.");
            }
        }
        return 0;
    }



}

