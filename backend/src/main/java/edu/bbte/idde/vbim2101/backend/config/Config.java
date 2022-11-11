package edu.bbte.idde.vbim2101.backend.config;

public class Config {
    private String daoType;
    private String url;
    private String database;
    private String username;
    private String password;

    public Config() {
        daoType = "mem";
        url = "";
        database = "";
        username = "";
        password = "";
    }

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDatabase() {
        return database;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Config{"
                + "daoType='" + daoType + '\''
                + '}';
    }
}
