package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.backend.model.Advertisement;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class AdvertisementMemoryDao implements AdvertisementsDao {
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
    }

    @Override
    public void updateAdvertisement(Long id, Advertisement advertisement) {
        Advertisement toUpdate = this.findById(id);
        toUpdate.setTitle(advertisement.getTitle());
        toUpdate.setAddress(advertisement.getAddress());
        toUpdate.setPrice(advertisement.getPrice());
        toUpdate.setSurfaceArea(advertisement.getSurfaceArea());
        toUpdate.setRooms(advertisement.getRooms());
    }

    @Override
    public void deleteAdvertisement(Long id) {
        ENTITIES.remove(id);
    }

    @Override
    public Collection<Advertisement> findAllAdvertisements() {
        return ENTITIES.values();
    }
}
