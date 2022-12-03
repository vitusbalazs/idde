package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.vbim2101.backend.config.Config;
import edu.bbte.idde.vbim2101.backend.config.ConfigFactory;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Slf4j
public class DataSourceFactory {
    private static DataSource dataSource;
    private static Boolean sqlInitialized = false;

    public static synchronized void initializeSQL() {
        if (!sqlInitialized) {
            try (Connection connection = dataSource.getConnection()) {
                PreparedStatement preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Owners ("
                        + "ID INT AUTO_INCREMENT PRIMARY KEY, "
                        + "name VARCHAR(100), "
                        + "email VARCHAR(200), "
                        + "age INT"
                        + ");");
                preparedStatement.executeUpdate();
                preparedStatement = connection.prepareStatement("CREATE TABLE IF NOT EXISTS Advertisements ("
                        + "ID INT AUTO_INCREMENT PRIMARY KEY,"
                        + "title VARCHAR(100),"
                        + "address VARCHAR(200),"
                        + "price INT,"
                        + "surfaceArea INT,"
                        + "rooms INT,"
                        + "owner INT,"
                        + "FOREIGN KEY (owner) REFERENCES Owners(ID)"
                        + ");");
                preparedStatement.executeUpdate();
                sqlInitialized = true;
            } catch (SQLException e) {
                log.error("[Advertisements - SQL] SQL initialization failed... ", e);
            }
        }
    }

    public static synchronized DataSource getDataSource() {
        if (dataSource == null) {
            Config config = ConfigFactory.getConfig();
            HikariConfig hikariConfig = new HikariConfig();

            hikariConfig.setDriverClassName(config.getJdbcDriver());

            hikariConfig.setJdbcUrl("jdbc:mysql://" + config.getUrl() + ":3306/" + config.getDatabase());
            hikariConfig.setUsername(config.getUsername());
            hikariConfig.setPassword(config.getPassword());

            hikariConfig.setMaximumPoolSize(config.getPoolsize());

            dataSource = new HikariDataSource(hikariConfig);
        }
        if (!sqlInitialized) {
            initializeSQL();
        }
        return dataSource;
    }
}
