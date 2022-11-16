package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.ShopsDao;
import edu.bbte.idde.vbim2101.backend.model.Shop;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MemShopsDao implements ShopsDao {
    private static final Map<Long, Shop> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    @Override
    public Shop findById(Long id) {
        return ENTITIES.get(id);
    }

    @Override
    public void create(Shop shop) {
        Long id = ID_GENERATOR.getAndIncrement();
        shop.setId(id);
        ENTITIES.put(id, shop);
        log.info("[DAO] Added new shop (Name=" + shop.getName() + ")");
    }

    @Override
    public void update(Long id, Shop shop) {
        Shop toUpdate = this.findById(id);
        toUpdate.setName(shop.getName());
        toUpdate.setAddress(shop.getAddress());
        toUpdate.setRating(shop.getRating());
        log.info("[DAO] Updated shop ((New)Name=" + shop.getName() + ")");
    }

    @Override
    public void delete(Long id) {
        log.info("[DAO] Deleting shop.. (Title=" + ENTITIES.get(id).getName() + ")");
        ENTITIES.remove(id);
        log.info("[DAO] Delete completed");
    }

    @Override
    public Collection<Shop> findAll() {
        log.info("[DAO] Finding all advertisements..");
        return ENTITIES.values();
    }
}
