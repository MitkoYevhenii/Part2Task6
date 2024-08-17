package org.example;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;



public class DatabaseInitService {
    public static void main(String[] args) throws SQLException {
        String dbUrl = "jdbc:h2:file:C:/Users/Admin/Documents/GoIT Homework/Part2Task6/src/main/java/org/example/sql/mydatabase";
        String dbUser = "Yevhenii";
        String dbPass = "";

        Connection conn = Database.getInstance(dbUrl, dbUser, dbPass).getConnection();
        Statement statement = conn.createStatement();

        String query = "SELECT name, birthday, level, salary, FROM worker";
        ResultSet rs = statement.executeQuery(query);
        while (rs.next()) {
            String name = rs.getString("name");
            LocalDate birthday = LocalDate.parse(rs.getString("birthday"));
            String level = rs.getString("level");
            int salary = rs.getInt("salary");

            System.out.println("name: " + name);
            System.out.println("birthday: " + birthday);
            System.out.println("level: " + level);
            System.out.println("salary: " + salary + "\n");
        }
    }
}
