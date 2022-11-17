package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.config.Config;
import edu.bbte.idde.vbim2101.backend.config.ConfigFactory;
import edu.bbte.idde.vbim2101.backend.dao.jdbc.JdbcDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.memory.MemDaoFactory;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public abstract class AbstractDaoFactory {
    private static AbstractDaoFactory instance;

    public abstract AdvertisementsDao getAdvertisementDao();

    public abstract OwnersDao getOwnersDao();

    public static synchronized AbstractDaoFactory getInstance() {
        if (instance == null) {
            Config config = ConfigFactory.getConfig();

            String daoType = System.getenv("daoType");
            log.info(daoType);
            if (daoType == null) {
                if (Objects.equals(config.getDaoType(), "jdbc")) {
                    log.info("yaml jdbc");
                    instance = new JdbcDaoFactory();
                } else {
                    log.info("yaml mem");
                    instance = new MemDaoFactory();
                }
            } else {
                if (Objects.equals(daoType, "jdbc")) {
                    log.info("env jdbc");
                    instance = new JdbcDaoFactory();
                } else {
                    log.info("env mem");
                    instance = new MemDaoFactory();
                }
            }
        }
        return instance;
    }
}
