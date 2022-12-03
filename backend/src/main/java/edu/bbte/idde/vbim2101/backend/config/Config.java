package edu.bbte.idde.vbim2101.backend.config;

import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class Config {
    private String daoType;
    private String url;
    private String database;
    private String username;
    private String password;
    private Integer poolsize;
    private String jdbcDriver;

    public Config() {
        daoType = "mem";
        url = "";
        database = "";
        username = "";
        password = "";
        poolsize = 0;
        jdbcDriver = "";
    }
}
