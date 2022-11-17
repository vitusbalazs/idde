package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AbstractDaoFactory;
import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.dao.OwnersDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MemAdvertisementDao implements AdvertisementsDao {
    private static final Map<Long, Advertisement> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    @Override
    public Advertisement findById(Long id) {
        log.info("[MemAdvertisement - DAO] Finding advertisement by ID...");
        return ENTITIES.get(id);
    }

    @Override
    public void create(Advertisement advertisement) {
        Long id = ID_GENERATOR.getAndIncrement();
        advertisement.setId(id);
        ENTITIES.put(id, advertisement);
        log.info("[MemAdvertisement - DAO] Added new advertisement (Title=" + advertisement.getTitle() + ")");
    }

    @Override
    public void update(Long id, Advertisement advertisement) {
        Advertisement toUpdate = this.findById(id);
        toUpdate.setTitle(advertisement.getTitle());
        toUpdate.setAddress(advertisement.getAddress());
        toUpdate.setPrice(advertisement.getPrice());
        toUpdate.setSurfaceArea(advertisement.getSurfaceArea());
        toUpdate.setRooms(advertisement.getRooms());
        toUpdate.setOwner(advertisement.getOwner());
        log.info("[MemAdvertisement - DAO] Updated advertisement ((New)Title=" + advertisement.getTitle() + ")");
    }

    @Override
    public void delete(Long id) {
        log.info("[MemAdvertisement - DAO] Deleting advertisement... (Title=" + ENTITIES.get(id).getTitle() + ")");
        ENTITIES.remove(id);
        log.info("[MemAdvertisement - DAO] Delete completed");
    }

    @Override
    public Collection<Advertisement> findAll() {
        log.info("[MemAdvertisement - DAO] Finding all advertisements...");
        return ENTITIES.values();
    }

    @Override
    public Collection<Advertisement> findByAge(Integer age) {
        log.info("[MemAdvertisement - DAO] Finding all advertisements by age of owner...");
        AbstractDaoFactory abstractDaoFactory = AbstractDaoFactory.getInstance();
        OwnersDao ownersDao = abstractDaoFactory.getOwnersDao();
        Collection<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement i:ENTITIES.values()) {
            if (Objects.equals(ownersDao.findById(i.getOwner()).getAge(), age)) {
                advertisements.add(i);
            }
        }
        return advertisements;
    }
}
