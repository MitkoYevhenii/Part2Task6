package org.example.storage;

import org.example.prefs.Prefs;
import org.example.prefs.SqlFileReader;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitService {
    public static void main(String[] args) {
        Prefs prefs = new Prefs();

        String dbUrl =  prefs.getString(Prefs.DB_JDBC_CONNECTION_URL);
        String dbUser = prefs.getString(Prefs.DB_JDBC_USERNAME);
        String dbPass = prefs.getString(Prefs.DB_JDBC_PASSWORD);
        String sqlPath = prefs.getString(Prefs.INIT_DB_FILE_PATH);

        try(Connection connection = Database.getInstance(dbUrl, dbUser, dbPass).getConnection();) {
            String sql = SqlFileReader.readSqlFile(sqlPath + "\\init_db.sql");
            Statement statement = connection.createStatement();
            statement.execute(sql);
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
}


