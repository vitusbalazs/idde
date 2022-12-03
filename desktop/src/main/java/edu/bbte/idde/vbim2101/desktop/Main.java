package edu.bbte.idde.vbim2101.desktop;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.OwnersDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import edu.bbte.idde.vbim2101.backend.model.Owner;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;

public class Main {
    private static final Logger LOG = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AbstractDaoFactory abstractDaoFactory = AbstractDaoFactory.getInstance();

        LOG.info("-------------- Owners --------------");
        OwnersDao ownersDao = abstractDaoFactory.getOwnersDao();
        Collection<Owner> lista2 = ownersDao.findAll();
        for (Owner i : lista2) {
            LOG.info("{}: {}", i.getId(), i);
        }

        LOG.info("-------------- Advertisements --------------");
        AdvertisementsDao adDao = abstractDaoFactory.getAdvertisementDao();
        adDao.create(new Advertisement("monostori elado", "monostor", 15, 1, 2, 1L));
        Collection<Advertisement> lista = adDao.findAll();
        for (Advertisement i : lista) {
            LOG.info("{}: {}", i.getId(), i);
        }
    }
}
