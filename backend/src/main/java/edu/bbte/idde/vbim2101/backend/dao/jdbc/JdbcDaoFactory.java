package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.DaoFactory;

public class JdbcDaoFactory extends DaoFactory {
    private static JdbcAdvertisementDao dao;

    @Override
    public AdvertisementsDao getAdvertisementDao() {
        if (dao == null) {
            dao = new JdbcAdvertisementDao();
        }
        return dao;
    }
}
