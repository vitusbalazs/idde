package edu.bbte.idde.vbim2101.spring.dao.mem;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Repository
@Slf4j
@Profile("mem")
public class MemOwnersDao implements OwnersDao {
    private static final Map<Long, Owner> ENTITIES = new ConcurrentHashMap<>();
    private static final AtomicLong ID_GENERATOR = new AtomicLong();

    @Override
    public Owner findById(Long id) {
        log.info("[MemOwners - DAO] Finding owner by id...");
        return ENTITIES.get(id);
    }

    @Override
    public Long create(Owner owner) {
        Long id = ID_GENERATOR.incrementAndGet();
        owner.setId(id);
        ENTITIES.put(id, owner);
        log.info("[MemOwners - DAO] Added new owner (Name=" + owner.getName() + ")");
        return id;
    }

    @Override
    public Boolean update(Long id, Owner owner) {
        Owner toUpdate = this.findById(id);
        if (Objects.isNull(toUpdate)) {
            return false;
        }
        toUpdate.setName(owner.getName());
        toUpdate.setEmail(owner.getEmail());
        toUpdate.setAge(owner.getAge());
        log.info("[MemOwners - DAO] Updated owner ((New)Name=" + owner.getName() + ")");
        return true;
    }

    @Override
    public Boolean delete(Long id) {
        log.info("[MemOwners - DAO] Deleting owner... (Title=" + ENTITIES.get(id).getName() + ")");
        Owner deleted = ENTITIES.remove(id);
        if (deleted == null) {
            log.error("[MemOwners - DAO] Owner not found! (ID=" + id + ")");
            return false;
        }
        log.info("[MemOwners - DAO] Delete completed");
        return true;
    }

    @Override
    public Collection<Owner> findAll() {
        log.info("[MemOwners - DAO] Finding all owners...");
        return ENTITIES.values();
    }

    @Override
    public Collection<Owner> findByAge(Integer age) {
        log.info("[MemOwners - DAO] Finding owners by age...");
        Collection<Owner> owners = new ArrayList<>();
        for (Owner i:ENTITIES.values()) {
            if (Objects.equals(i.getAge(), age)) {
                owners.add(i);
            }
        }
        return owners;
    }
}