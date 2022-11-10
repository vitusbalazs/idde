package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.DaoFactory;

public class jdbcDaoFactory extends DaoFactory {
    private static jdbcAdvertisementDao dao;
    @Override
    public AdvertisementsDao getAdvertisementDao() {
        if (dao == null) {
            dao = new jdbcAdvertisementDao();
        }
        return dao;
    }
}
