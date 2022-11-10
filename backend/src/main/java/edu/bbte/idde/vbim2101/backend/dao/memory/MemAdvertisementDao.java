package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemAdvertisementDao implements AdvertisementsDao {
    private static final Logger LOG = LoggerFactory.getLogger(MemAdvertisementDao.class);
    private static final Map<Long, Advertisement> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

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
