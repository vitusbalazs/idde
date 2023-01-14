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
    private String url;
    @Value("${jdbc.username:root}")
    private String userName;
    @Value("${jdbc.password:root}")
    private String passWord;
    @Value("${jdbc.poolSize:10}")
    private Integer poolSize;
    @Value("${jdbc.jdbcDriver:com.mysql.cj.jdbc.Driver}")
    private String driverClass;

    @Bean
    public DataSource getDataSource() {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setDriverClassName(driverClass);
        hikariConfig.setJdbcUrl(url);
        hikariConfig.setUsername(userName);
        hikariConfig.setPassword(passWord);
        hikariConfig.setMaximumPoolSize(poolSize);

        return new HikariDataSource(hikariConfig);
    }
}
