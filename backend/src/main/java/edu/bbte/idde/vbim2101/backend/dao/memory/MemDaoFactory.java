package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.DaoFactory;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;

public class MemDaoFactory extends DaoFactory {
    private static MemAdvertisementDao dao;

    @Override
    public AdvertisementsDao getAdvertisementDao() {
        if (dao == null) {
            dao = new MemAdvertisementDao();
            dao.createAdvertisement(
                    new Advertisement("Elado monostori lakas", "Portile de fier 4", 150000, 60, 1)
            );
            dao.createAdvertisement(
                    new Advertisement("Elado marasti negyedi lakas", "Piata Abator 4-7", 150000, 115, 3)
            );
            dao.createAdvertisement(
                    new Advertisement("Elado grigorescu negyedi lakas", "Bartok Bela 15", 95000, 72, 4)
            );

        }
        return dao;
    }
}
