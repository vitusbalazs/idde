package edu.bbte.idde.vbim2101.backend.dao.jdbc;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.ShopsDao;

public class JdbcDaoFactory extends AbstractDaoFactory {
    private static JdbcAdvertisementDao advDao;
    private static JdbcShopDao shopDao;

    @Override
    public synchronized AdvertisementsDao getAdvertisementDao() {
        if (advDao == null) {
            advDao = new JdbcAdvertisementDao();
        }
        return advDao;
    }

    @Override
    public synchronized ShopsDao getShopsDao() {
        if (shopDao == null) {
            shopDao = new JdbcShopDao();
        }
        return shopDao;
    }
}
