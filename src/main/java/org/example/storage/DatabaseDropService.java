package org.example.storage;

import org.example.prefs.Prefs;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseDropService {
    public static void main(String[] args) {
        Prefs prefs = new Prefs();

        String dbUrl =  prefs.getString(Prefs.DB_JDBC_CONNECTION_URL);
        String dbUser = prefs.getString(Prefs.DB_JDBC_USERNAME);
        String dbPass = prefs.getString(Prefs.DB_JDBC_PASSWORD);

        try(Connection connection = Database.getInstance(dbUrl, dbUser, dbPass).getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DROP TABLE CLIENT, PROJECT, PROJECT_WORKER, WORKER");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

