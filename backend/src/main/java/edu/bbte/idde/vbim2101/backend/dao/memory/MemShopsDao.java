package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.ShopsDao;
import edu.bbte.idde.vbim2101.backend.model.Shop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class MemShopsDao implements ShopsDao {
    private static final Logger LOG = LoggerFactory.getLogger(MemShopsDao.class);
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
        LOG.info("[DAO] Added new shop (Name=" + shop.getName() + ")");
    }

    @Override
    public void update(Long id, Shop shop) {
        Shop toUpdate = this.findById(id);
        toUpdate.setName(shop.getName());
        toUpdate.setAddress(shop.getAddress());
        toUpdate.setRating(shop.getRating());
        LOG.info("[DAO] Updated shop ((New)Name=" + shop.getName() + ")");
    }

    @Override
    public void delete(Long id) {
        LOG.info("[DAO] Deleting shop.. (Title=" + ENTITIES.get(id).getName() + ")");
        ENTITIES.remove(id);
        LOG.info("[DAO] Delete completed");
    }

    @Override
    public Collection<Shop> findAll() {
        LOG.info("[DAO] Finding all advertisements..");
        return ENTITIES.values();
    }
}
