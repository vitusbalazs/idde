package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import edu.bbte.idde.vbim2101.spring.controller.mapper.AdvertisementsMapper;
import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementOutDto;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/advertisements")
public class AdvertisementsController {
    @Autowired
    private AdvertisementsDao advertisementsDao;
    @Autowired
    private AdvertisementsMapper advertisementsMapper;
    @Autowired
    private OwnersDao ownersDao;

    @GetMapping
    public Collection<AdvertisementOutDto> findAll(@RequestParam(required = false) Integer rooms) {
        if (rooms == null) {
            return advertisementsMapper.dtosFromAdvertisements(advertisementsDao.findAll());
        }
        Collection<Advertisement> advertisements = advertisementsDao.findByRooms(rooms);
        return advertisementsMapper.dtosFromAdvertisements(advertisements);
    }

    @GetMapping("/{id}")
    public AdvertisementOutDto findById(@PathVariable("id") Long id) {
        Advertisement result = advertisementsDao.getById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return advertisementsMapper.dtoFromAdvertisement(result);
    }

    @PostMapping
    public String create(@RequestBody @Valid AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        Owner owner = ownersDao.getById(advertisementInDto.getOwner());
        advertisement.setOwner(owner);
        owner.getAdvertisements().add(advertisement);
        Long id = advertisementsDao.saveAndFlush(advertisement).getId();
        if (id == null) {
            log.error("Failed to create advertisement");
            return "Failed to create advertisement";
        } else {
            log.info("Created advertisement with id: " + id);
            return "Created advertisement with id: " + id;
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody @Valid AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        advertisement.setId(id);
        advertisement.setOwner(ownersDao.getById(advertisementInDto.getOwner()));
        advertisementsDao.saveAndFlush(advertisement);
        Boolean success = advertisementsDao.getById(id) != null;
        if (success) {
            log.info("Updated advertisement with id: " + id);
            return "Updated advertisement with id: " + id;

        } else {
            log.error("Failed to update advertisement with id: " + id);
            return "Failed to update advertisement with id: " + id;
        }
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        advertisementsDao.delete(advertisementsDao.getById(id));
        // Boolean success = advertisementsDao.getById(id) == null;
        /* if (success) {
            log.info("Deleted advertisement with id: " + id);
            return "Deleted advertisement with id: " + id;
        } else {
            log.error("Failed to delete advertisement with id: " + id);
            return "Failed to delete advertisement with id: " + id;
        } */
    }
}
