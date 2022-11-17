package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import edu.bbte.idde.vbim2101.backend.model.Owner;

public class MemDaoFactory extends AbstractDaoFactory {
    private static MemAdvertisementDao advDao;
    private static MemOwnersDao ownerDao;

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
    public synchronized MemOwnersDao getOwnersDao() {
        if (ownerDao == null) {
            ownerDao = new MemOwnersDao();
            ownerDao.create(
                    new Owner("Jakab-Gyik Sarolta", "jgysarolta@gmail.com", 20)
            );
            ownerDao.create(
                    new Owner("Vitus Balazs", "vitusbalazs01@yahoo.com", 21)
            );
            ownerDao.create(
                    new Owner("Gyula aluGy", "alugyjol@gyula.com", 20)
            );
        }
        return ownerDao;
    }
}
