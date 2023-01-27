package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import edu.bbte.idde.vbim2101.spring.controller.mapper.AdvertisementsMapper;
import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementOutDto;
import edu.bbte.idde.vbim2101.spring.model.Owner;
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
@RequestMapping("/advertisements")
public class AdvertisementsController {
    @Autowired
    private AdvertisementsDao advertisementsDao;
    @Autowired
    private AdvertisementsMapper advertisementsMapper;
    @Autowired
    private QueryDao queryDao;
    @Autowired
    private OwnersDao ownersDao;

    @GetMapping
    public Collection<AdvertisementOutDto> findAll(@RequestParam(required = false) Integer rooms) {
        if (rooms == null) {
            queryDao.saveAndFlush(new Queries("Find all advertisements", Timestamp.from(Instant.now())));
            return advertisementsMapper.dtosFromAdvertisements(advertisementsDao.findAll());
        }
        Collection<Advertisement> advertisements = advertisementsDao.findByRooms(rooms);
        queryDao.saveAndFlush(new Queries("Find advertisements with"
                + rooms + " rooms", Timestamp.from(Instant.now())));
        return advertisementsMapper.dtosFromAdvertisements(advertisements);
    }

    @GetMapping("/{id}")
    public AdvertisementOutDto findById(@PathVariable("id") Long id) {
        Advertisement result = advertisementsDao.getById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        queryDao.saveAndFlush(new Queries("Find advertisement by id", Timestamp.from(Instant.now())));
        return advertisementsMapper.dtoFromAdvertisement(result);
    }

    @PostMapping
    public String create(@RequestBody @Valid AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        Owner owner = ownersDao.getById(advertisementInDto.getOwner());
        owner.getAdvertisements().add(advertisement);
        advertisement.setOwner(owner);

        Advertisement newAdvertisement = advertisementsDao.saveAndFlush(advertisement);
        if (newAdvertisement == null) {
            log.error("Failed to create advertisement");
            return "Failed to create advertisement";
        } else {
            log.info("Created advertisement with id: " + newAdvertisement.getId());
            return "Created advertisement with id: " + newAdvertisement.getId();
        }
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, @RequestBody @Valid AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        advertisement.setId(id);
        advertisement.setOwner(ownersDao.getById(advertisementInDto.getOwner()));
        advertisementsDao.saveAndFlush(advertisement);
        return "Update done";
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        advertisementsDao.delete(advertisementsDao.getById(id));
    }
}
