package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AdvertisementMemoryDao implements AdvertisementsDao {
    private static final Logger LOG = LoggerFactory.getLogger(AdvertisementMemoryDao.class);
    private static final Map<Long, Advertisement> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    static {
        Advertisement advertisement = new Advertisement("Elado monostori lakas", "Portile de fier 4", 150000, 60, 1);
        Long id = ID_GENERATOR.getAndIncrement();
        advertisement.setId(id);
        ENTITIES.put(id, advertisement);
        advertisement = new Advertisement("Elado marasti negyedi lakas", "Piata Abator 4-7", 150000, 115, 3);
        id = ID_GENERATOR.getAndIncrement();
        advertisement.setId(id);
        ENTITIES.put(id, advertisement);
        advertisement = new Advertisement("Elado grigorescu negyedi lakas", "Bartok Bela 15", 95000, 72, 4);
        id = ID_GENERATOR.getAndIncrement();
        advertisement.setId(id);
        ENTITIES.put(id, advertisement);
    }

    @Override
    public Advertisement findById(Long id) {
        return ENTITIES.get(id);
    }

    @Override
    public void createAdvertisement(Advertisement advertisement) {
        Long id = ID_GENERATOR.getAndIncrement();
        advertisement.setId(id);
        ENTITIES.put(id, advertisement);
        LOG.info("[DAO] Added new advertisement (Title=" + advertisement.getTitle() + ")");
    }

    @Override
    public void updateAdvertisement(Long id, Advertisement advertisement) {
        Advertisement toUpdate = this.findById(id);
        toUpdate.setTitle(advertisement.getTitle());
        toUpdate.setAddress(advertisement.getAddress());
        toUpdate.setPrice(advertisement.getPrice());
        toUpdate.setSurfaceArea(advertisement.getSurfaceArea());
        toUpdate.setRooms(advertisement.getRooms());
        LOG.info("[DAO] Updated advertisement ((New)Title=" + advertisement.getTitle() + ")");
    }

    @Override
    public void deleteAdvertisement(Long id) {
        LOG.info("[DAO] Deleting advertisement.. (Title=" + ENTITIES.get(id).getTitle() + ")");
        ENTITIES.remove(id);
        LOG.info("[DAO] Delete completed");
    }

    @Override
    public Collection<Advertisement> findAllAdvertisements() {
        LOG.info("[DAO] Finding all advertisements..");
        return ENTITIES.values();
    }
}
