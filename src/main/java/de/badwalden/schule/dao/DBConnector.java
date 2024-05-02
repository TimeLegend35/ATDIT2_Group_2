package de.badwalden.schule.dao;

import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.helper.LanguageHelper;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.scene.control.Alert;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Singleton class to handle database connections and operations.
 */
public class DBConnector {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    private Connection connection;
    private static DBConnector instance;
    private static final Dotenv env = Dotenv.configure().load();

    /**
     * Private constructor for creating a DBConnector instance.
     * Initializes the connection by calling the connect method.
     */
    private DBConnector() {
        this.connection = connect();
    }

    /**
     * Gets the single instance of the DBConnector class, creating it if it does not yet exist.
     *
     * @return the single instance of DBConnector
     */
    public static synchronized DBConnector getInstance() {

        if (instance == null) {
            instance = new DBConnector();
        }
        return instance;
    }

    /**
     * Establishes a database connection using a connection URL from the environment configuration.
     * Throws IllegalArgumentException if the URL is missing or empty, and SQLException on connection failure.
     *
     * @return a new Connection object
     * @throws SQLException if a database access error occurs
     * @throws IllegalArgumentException if the connection URL is null or empty
     */
    public Connection connect() {

        String large_url = env.get("CONNECTION_URL");

        try {
            if (large_url == null || large_url.isEmpty()) {
                throw new IllegalArgumentException(LanguageHelper.getString("url_empty"));
            }

            return DriverManager.getConnection(large_url);
        } catch (IllegalArgumentException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("url_empty_log") + e.getMessage(), e);
            // Additional handling or user notification can be done here
            throw e;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("sql_exception"), e);
            return scheduleReconnection();
        }
    }

    /**
     * Attempts to re-establish a database connection if the initial connection fails.
     * Displays an error dialog if reconnection fails.
     *
     * @return a new or null Connection object depending on whether the connection attempt succeeds
     */
    private Connection scheduleReconnection() {

        String large_url = env.get("CONNECTION_URL");

        DialogHelper.showTimedAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("db_connection_error"),
                LanguageHelper.getString("reconnection_error"), 31);

        try {
            return DriverManager.getConnection(large_url);
        } catch (Exception e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("reconnection_failed"), e);
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR,"Reconnection failed", "Please Restart the program");
            return null;
        }
    }

    /**
     * Closes the current database connection.
     * Logs and shows an error if the closing fails.
     */
    private void close() {

        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("db_close_log"), e);
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("db_connection_error"), LanguageHelper.getString("db_close_error"));
        }
    }

    /**
     * Executes a SQL query with the specified parameters and returns the result as a list of Object arrays.
     *
     * @param sql the SQL query to execute
     * @param params the parameters for the SQL query
     * @return a List of Object arrays, each representing a row of the result set
     */
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
                logger.log(Level.SEVERE, LanguageHelper.getString("excecute_sql_log"), e);
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("db_connection_error"), LanguageHelper.getString("excecute_sql_error"));
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
                logger.log(Level.SEVERE, LanguageHelper.getString("excecute_update_log"), e);
                DialogHelper.showAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("db_connection_error"), LanguageHelper.getString("excecute_update_error"));
            }
        }
        return 0;
    }

}

