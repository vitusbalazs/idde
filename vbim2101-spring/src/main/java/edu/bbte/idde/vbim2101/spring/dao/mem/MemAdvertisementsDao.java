package edu.bbte.idde.vbim2101.spring.dao.mem;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Slf4j
@Profile("mem")
public class MemAdvertisementsDao implements AdvertisementsDao {
    private static final ConcurrentHashMap<Long, Advertisement> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong(0L);

    @Override
    public Collection<Advertisement> findByRooms(Integer rooms) {
        log.info("[MemAdvertisement - DAO] Finding all advertisements by rooms number...");
        Collection<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement i : ENTITIES.values()) {
            if (Objects.equals(i.getRooms(), rooms)) {
                advertisements.add(i);
            }
        }
        return advertisements;
    }

    @Override
    public Collection<Advertisement> findAll() {
        log.info("[MemAdvertisement - DAO] Finding all advertisements...");
        return ENTITIES.values();
    }

    @Override
    public Advertisement findById(Long id) {
        log.info("[MemAdvertisement - DAO] Finding advertisement by ID...");
        return ENTITIES.get(id);
    }

    @Override
    public void create(Advertisement advertisement) {
        Long id = ID_GENERATOR.incrementAndGet();
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
}
