package edu.bbte.idde.vbim2101.spring.dao.jdbc;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import javax.sql.DataSource;

@Configuration
@Slf4j
@Profile("!mem")
public class DataSourceFactory {
    @Value("${jdbc.url:localhost}")
    private String jdbcUrl;
    @Value("${jdbc.username:root}")
    private String jdbcUser;
    @Value("${jdbc.password:root}")
    private String jdbcPassword;
    @Value("${jdbc.database:idde_vbim2101}")
    private String jdbcDatabase;
    @Value("${jdbc.poolSize:10}")
    private Integer jdbcPoolSize;
    @Value("${jdbc.jdbcDriver:com.mysql.cj.jdbc.Driver}")
    private String jdbcDriverClassName;

    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://" + jdbcUrl + "/" + jdbcDatabase + "?useSSL=false");
        hikariConfig.setUsername(jdbcUser);
        hikariConfig.setPassword(jdbcPassword);

        hikariConfig.setMaximumPoolSize(jdbcPoolSize);
        hikariConfig.setDriverClassName(jdbcDriverClassName);

        return new HikariDataSource(hikariConfig);
    }
}
