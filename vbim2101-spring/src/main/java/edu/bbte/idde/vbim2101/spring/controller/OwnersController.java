package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import edu.bbte.idde.vbim2101.spring.model.OwnersMapper;
import edu.bbte.idde.vbim2101.spring.model.dto.OwnerInDto;
import edu.bbte.idde.vbim2101.spring.model.dto.OwnerOutDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/owners")
@Slf4j
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
        if (owners.isEmpty()) {
            throw new NotFoundException();
        }
        return ownersMapper.dtosFromOwners(owners);
    }

    @GetMapping("/{id}")
    public OwnerOutDto findById(@PathVariable("id") Long id) {
        Owner result = ownersDao.findById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return ownersMapper.dtoFromOwner(result);
    }

    @PostMapping
    public void create(@RequestBody @Valid OwnerInDto ownerInDto) {
        ownersDao.create(ownersMapper.ownerFromDto(ownerInDto));
    }

    @PutMapping
    public void update(@RequestParam(required = true) Long id,
                       @RequestBody @Valid OwnerInDto ownerInDto) {
        if (ownersDao.findById(id) == null) {
            throw new NotFoundException();
        }
        Owner owner = ownersMapper.ownerFromDto(ownerInDto);
        owner.setId(id);
        ownersDao.update(id, owner);
    }

    @DeleteMapping
    public void delete(@RequestParam(required = true) Long id) {
        if (ownersDao.findById(id) == null) {
            throw new NotFoundException();
        }
        ownersDao.delete(id);
    }
}
