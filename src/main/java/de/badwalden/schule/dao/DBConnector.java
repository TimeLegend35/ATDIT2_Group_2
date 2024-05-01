package de.badwalden.schule.dao;

import de.badwalden.schule.model.Student;
import de.badwalden.schule.ui.helper.DialogHelper;
import de.badwalden.schule.ui.helper.LanguageHelper;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.control.Alert;
import javafx.util.Duration;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnector {
    private static final Logger logger = Logger.getLogger(DBConnector.class.getName());
    private Connection connection;
    private static DBConnector instance;
    private static final Dotenv env = Dotenv.configure().load();

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

    private Connection scheduleReconnection() {
        String large_url = env.get("CONNECTION_URL");

        DialogHelper.showTimedAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("db_connection_error"),
                LanguageHelper.getString("reconnection_error"), 31);

        try {
            return DriverManager.getConnection(large_url);
        } catch (Exception e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("reconnection_failed"), e);
            return null;
        }
    }

    private void close() {
        try {
            this.connection.close();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, LanguageHelper.getString("db_close_log"), e);
            DialogHelper.showAlertDialog(Alert.AlertType.ERROR, LanguageHelper.getString("db_connection_error"), LanguageHelper.getString("db_close_error"));
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

