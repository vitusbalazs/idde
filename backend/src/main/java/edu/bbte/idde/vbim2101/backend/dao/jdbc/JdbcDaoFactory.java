package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.OwnersDao;

public class JdbcDaoFactory extends AbstractDaoFactory {
    private static JdbcAdvertisementDao advDao;
    private static JdbcOwnerDao shopDao;

    @Override
    public synchronized AdvertisementsDao getAdvertisementDao() {
        if (advDao == null) {
            advDao = new JdbcAdvertisementDao();
        }
        return advDao;
    }

    @Override
    public synchronized OwnersDao getOwnersDao() {
        if (shopDao == null) {
            shopDao = new JdbcOwnerDao();
        }
        return shopDao;
    }
}
