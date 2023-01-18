package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import edu.bbte.idde.vbim2101.spring.controller.mapper.OwnersMapper;
import edu.bbte.idde.vbim2101.spring.controller.dto.OwnerInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.OwnerOutDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/owners")
public class OwnersController {
    @Autowired
    private OwnersDao ownersDao;
    @Autowired
    private OwnersMapper ownersMapper;

    @GetMapping
    public Collection<OwnerOutDto> findAll(@RequestParam(required = false) Integer age) {
        if (age == null) {
            return ownersMapper.dtosFromOwners(ownersDao.findAll());
        }
        Collection<Owner> owners = ownersDao.findByAge(age);
        return ownersMapper.dtosFromOwners(owners);
    }

    @GetMapping("/{id}")
    public OwnerOutDto findById(@PathVariable("id") Long id) {
        Owner result = ownersDao.getById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return ownersMapper.dtoFromOwner(result);
    }

    @PostMapping
    public String create(@RequestBody @Valid OwnerInDto ownerInDto) {

        Long id = ownersDao.saveAndFlush(ownersMapper.ownerFromDto(ownerInDto)).getId();
        if (id == null) {
            log.error("Failed to create owner");
            return "Failed to create owner";
        } else {
            log.info("Created owner with id: " + id);
            return "Created owner with id: " + id;
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody @Valid OwnerInDto ownerInDto) {
        Owner owner = ownersMapper.ownerFromDto(ownerInDto);
        owner.setId(id);
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

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        ownersDao.delete(ownersDao.getById(id));
        // Boolean success = ownersDao.getById(id) == null;
        /* if (success) {
            log.info("Deleted owner with id: " + id);
            return "Deleted owner with id: " + id;
        } else {
            log.error("Failed to delete owner with id: " + id);
            return "Failed to delete owner with id: " + id;
        } */
    }
}
