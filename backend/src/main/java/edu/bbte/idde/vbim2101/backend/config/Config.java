package edu.bbte.idde.vbim2101.backend.config;

public class Config {
    private String daoType;

    public Config() {
        daoType = "mem";
    }

    public String getDaoType() {
        return daoType;
    }

    public void setDaoType(String daoType) {
        this.daoType = daoType;
    }

    @Override
    public String toString() {
        return "Config{"
                + "daoType='" + daoType + '\''
                + '}';
    }
}
