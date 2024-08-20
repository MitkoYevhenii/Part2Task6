package org.example.databaseQueryService;

import org.example.prefs.Prefs;
import org.example.prefs.SqlFileReader;
import org.example.storage.Database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


public class DatabaseQueryService {
    private final Connection connection;
    private String sqlFilePath;

    public DatabaseQueryService() {

        Prefs prefs = new Prefs();
        String dbUrl =  prefs.getString(Prefs.DB_JDBC_CONNECTION_URL);
        String dbUser = prefs.getString(Prefs.DB_JDBC_USERNAME);
        String dbPass = prefs.getString(Prefs.DB_JDBC_PASSWORD);

        connection = Database.getInstance(dbUrl, dbUser, dbPass).getConnection();
        sqlFilePath = prefs.getString(Prefs.INIT_DB_FILE_PATH);
    }

    private ResultSet executeQuery(String sql) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.executeQuery(sql);
    }


    public List<MaxProjectCountClient> maxProjectsClient() {
        List<MaxProjectCountClient> maxProjectCountClients = new ArrayList<>();
        try {
            String sql = SqlFileReader.readSqlFile(sqlFilePath + "\\find_max_projects_client.sql");
            ResultSet resultSet = executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int projectCount = resultSet.getInt("project_count");
                maxProjectCountClients.add(new MaxProjectCountClient(name, projectCount));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return maxProjectCountClients;
    }


    public List<FindLongestProject> longestProjects() {
        List<FindLongestProject> findLongestProjects = new ArrayList<>();
        try {
            String sql = SqlFileReader.readSqlFile(sqlFilePath + "\\find_longest_project.sql");
            ResultSet resultSet = executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                int monthCount = resultSet.getInt("month_count");
                findLongestProjects.add(new FindLongestProject(name, monthCount));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return findLongestProjects;
    }

    public List<ProjectPrice> projectPrices() {
        List<ProjectPrice> projectPrices = new ArrayList<>();
        try {
            String sql = SqlFileReader.readSqlFile(sqlFilePath + "\\print_project_prices.sql");
            ResultSet resultSet = executeQuery(sql);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String price = resultSet.getString("price");
                projectPrices.add(new ProjectPrice(name, price));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return projectPrices;
    }

    public List<YoungestAndEldestWorkers> yongersoungestAndEldestWorkers() {
        List<YoungestAndEldestWorkers> workers = new ArrayList<>();
        try {
            String sql = SqlFileReader.readSqlFile(sqlFilePath + "\\find_youngest_eldest_workers.sql");
            ResultSet resultSet = executeQuery(sql);

            while (resultSet.next()) {
                String type = resultSet.getString("type");
                String name = resultSet.getString("name");
                String birthday = resultSet.getString("birthday");
                workers.add(new YoungestAndEldestWorkers(type, name, birthday));
            }
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        return workers;
    }


    public static void main(String[] args) {
        List<MaxProjectCountClient> maxProjectCountClients = new DatabaseQueryService().maxProjectsClient();
        List<FindLongestProject> findLongestProjects = new DatabaseQueryService().longestProjects();
        List<ProjectPrice> projectPrices = new DatabaseQueryService().projectPrices();
        List<YoungestAndEldestWorkers> youngestAndEldestWorkers = new DatabaseQueryService()
                .yongersoungestAndEldestWorkers();
    }
}

