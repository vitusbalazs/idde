package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import edu.bbte.idde.vbim2101.backend.model.Shop;

public class MemDaoFactory extends AbstractDaoFactory {
    private static MemAdvertisementDao advDao;
    private static MemShopsDao shopDao;

    @Override
    public synchronized AdvertisementsDao getAdvertisementDao() {
        if (advDao == null) {
            advDao = new MemAdvertisementDao();
            advDao.create(
                    new Advertisement("Elado monostori lakas", "Portile de fier 4", 150000, 60, 1)
            );
            advDao.create(
                    new Advertisement("Elado marasti negyedi lakas", "Piata Abator 4-7", 150000, 115, 3)
            );
            advDao.create(
                    new Advertisement("Elado grigorescu negyedi lakas", "Bartok Bela 15", 95000, 72, 4)
            );

        }
        return advDao;
    }

    @Override
    public synchronized MemShopsDao getShopsDao() {
        if (shopDao == null) {
            shopDao = new MemShopsDao();
            shopDao.create(
                    new Shop("Mega Image 1", "Str. Livezii 5", 5)
            );
            shopDao.create(
                    new Shop("Kaufland 1", "Str. Fabricii", 4)
            );
            shopDao.create(
                    new Shop("LIDL", "Str. Morii", 2)
            );
        }
        return shopDao;
    }
}
