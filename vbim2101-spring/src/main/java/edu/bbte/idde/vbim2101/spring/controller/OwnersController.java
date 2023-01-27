package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import edu.bbte.idde.vbim2101.spring.controller.mapper.OwnersMapper;
import edu.bbte.idde.vbim2101.spring.controller.dto.OwnerInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.OwnerOutDto;
import edu.bbte.idde.vbim2101.spring.model.Queries;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/owners")
public class OwnersController {
    @Autowired
    private OwnersDao ownersDao;
    @Autowired
    private QueryDao queryDao;
    @Autowired
    private OwnersMapper ownersMapper;

    @GetMapping
    public Collection<OwnerOutDto> findAll(@RequestParam(required = false) Integer age) {
        if (age == null) {
            queryDao.saveAndFlush(new Queries("Find all owners", Timestamp.from(Instant.now())));
            return ownersMapper.dtosFromOwners(ownersDao.findAll());
        }
        Collection<Owner> owners = ownersDao.findByAge(age);
        queryDao.saveAndFlush(new Queries("Find Owners with " + age + " age", Timestamp.from(Instant.now())));
        return ownersMapper.dtosFromOwners(owners);
    }

    @GetMapping("/{id}")
    public OwnerOutDto findById(@PathVariable("id") Long id) {
        Owner result = ownersDao.getById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        queryDao.saveAndFlush(new Queries("Find by id owner", Timestamp.from(Instant.now())));
        return ownersMapper.dtoFromOwner(result);
    }

    @PostMapping
    public String create(@RequestBody @Valid OwnerInDto ownerInDto) {
        Owner owner = ownersMapper.ownerFromDto(ownerInDto);

        Owner newOwner = ownersDao.saveAndFlush(owner);
        if (newOwner == null) {
            log.error("Failed to create owner");
            return "Failed to create owner";
        } else {
            log.info("Created owner with id: " + newOwner.getId());
            return "Created owner with id: " + newOwner.getId();
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody @Valid OwnerInDto ownerInDto) {
        Owner owner = ownersMapper.ownerFromDto(ownerInDto);
        owner.setId(id);
        owner.setAdvertisements(ownersDao.getById(id).getAdvertisements());
        ownersDao.saveAndFlush(owner);
        Boolean success = ownersDao.getById(id) != null;
        if (success) {
            log.info("Updated owner with id: " + id);
            return "Updated owner with id: " + id;
        } else {
            log.error("Failed to update owner with id: " + id);
            return "Failed to update owner with id: " + id;
        }
    }

    // This doesn't work with JPA
    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ownersDao.delete(ownersDao.getById(id));
    }
}
