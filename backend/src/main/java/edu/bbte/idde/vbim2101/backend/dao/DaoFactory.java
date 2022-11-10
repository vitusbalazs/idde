package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.config.Config;
import edu.bbte.idde.vbim2101.backend.config.ConfigFactory;
import edu.bbte.idde.vbim2101.backend.dao.jdbc.jdbcDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.memory.MemDaoFactory;

import java.util.Objects;

public abstract class DaoFactory {
    private static DaoFactory instance;

    public abstract AdvertisementsDao getAdvertisementDao();

    public static synchronized DaoFactory getInstance() {
        if (instance == null) {
            Config config = ConfigFactory.getConfig();

            if (Objects.equals(config.getDaoType(), "jdbc")) {
                instance = new jdbcDaoFactory();
            } else {
                instance = new MemDaoFactory();
            }
        }
        return instance;
    }
}
