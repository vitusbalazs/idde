package edu.bbte.idde.vbim2101.desktop;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.ShopsDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import edu.bbte.idde.vbim2101.backend.model.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AbstractDaoFactory abstractDaoFactory = AbstractDaoFactory.getInstance();

        /*Advertisement ad1 = new Advertisement("Elado monostori lakas", "Portile de fier 4", 150000, 60, 1);
        adDao.createAdvertisement(ad1);
        LOG.info(ad1.toString());
        Advertisement toUpdate = new Advertisement("Elado monostori lakas", "Portile de fier 4", 160000, 60, 1);
        adDao.updateAdvertisement(ad1.getId(), toUpdate);
        LOG.info(ad1.toString());

        Advertisement ad2 = new Advertisement("Elado marasti negyedi lakas", "Piata Abator 4-7", 150000, 115, 3);
        adDao.createAdvertisement(ad2);
        LOG.info("Meg egy: {}", ad2);

        adDao.deleteAdvertisement(ad1.getId());

        ad1 = new Advertisement("Elado grigorescu negyedi lakas", "Bartok Bela 15", 95000, 72, 4);
        adDao.createAdvertisement(ad1);*/

        LOG.info("-------------- Advertisements --------------");
        AdvertisementsDao adDao = abstractDaoFactory.getAdvertisementDao();
        Collection<Advertisement> lista = adDao.findAll();
        for (Advertisement i : lista) {
            LOG.info("{}: {}", i.getId(), i);
        }

        LOG.info("-------------- Shops --------------");
        ShopsDao shopsDao = abstractDaoFactory.getShopsDao();
        Collection<Shop> lista2 = shopsDao.findAll();
        for (Shop i : lista2) {
            LOG.info("{}: {}", i.getId(), i);
        }
    }
}
