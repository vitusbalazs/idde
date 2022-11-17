package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import edu.bbte.idde.vbim2101.backend.config.Config;
import edu.bbte.idde.vbim2101.backend.config.ConfigFactory;

import javax.sql.DataSource;

public class DataSourceFactory {
    private static DataSource dataSource;

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
        return dataSource;
    }
}
