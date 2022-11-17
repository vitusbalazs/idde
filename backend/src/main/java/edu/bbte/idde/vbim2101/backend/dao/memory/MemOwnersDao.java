package edu.bbte.idde.vbim2101.backend.dao.memory;

import edu.bbte.idde.vbim2101.backend.dao.OwnersDao;
import edu.bbte.idde.vbim2101.backend.model.Owner;
import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Slf4j
public class MemOwnersDao implements OwnersDao {
    private static final Map<Long, Owner> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    @Override
    public Owner findById(Long id) {
        return ENTITIES.get(id);
    }

    @Override
    public void create(Owner owner) {
        Long id = ID_GENERATOR.getAndIncrement();
        owner.setId(id);
        ENTITIES.put(id, owner);
        log.info("[DAO] Added new owner (Name=" + owner.getName() + ")");
    }

    @Override
    public void update(Long id, Owner owner) {
        Owner toUpdate = this.findById(id);
        toUpdate.setName(owner.getName());
        toUpdate.setEmail(owner.getEmail());
        toUpdate.setAge(owner.getAge());
        log.info("[DAO] Updated owner ((New)Name=" + owner.getName() + ")");
    }

    @Override
    public void delete(Long id) {
        log.info("[DAO] Deleting owner.. (Title=" + ENTITIES.get(id).getName() + ")");
        ENTITIES.remove(id);
        log.info("[DAO] Delete completed");
    }

    @Override
    public Collection<Owner> findAll() {
        log.info("[DAO] Finding all owners..");
        return ENTITIES.values();
    }
}
