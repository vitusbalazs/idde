package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementInDto;
import edu.bbte.idde.vbim2101.spring.controller.dto.AdvertisementOutDto;
import edu.bbte.idde.vbim2101.spring.controller.mapper.AdvertisementsMapper;
import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@Slf4j
@RestController
@RequestMapping("/owner")
public class CommonController {
    @Autowired
    private OwnersDao ownersDao;
    @Autowired
    private AdvertisementsMapper advertisementsMapper;

    @GetMapping("/{id}/advertisements")
    public Collection<AdvertisementOutDto> findByOwner(@PathVariable("id") Long id) {
        log.info("[Common - Controller] Finding all advertisements by owner...");
        Collection<Advertisement> advertisements = ownersDao.getById(id).getAdvertisements();
        if (advertisements.isEmpty()) {
            throw new NotFoundException();
        }
        return advertisementsMapper.dtosFromAdvertisements(advertisements);
    }

    @PostMapping("/{id}/advertisement")
    public void create(@PathVariable("id") Long id, @RequestBody AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        Owner owner = ownersDao.getById(id);
        advertisement.setOwner(owner);
        owner.getAdvertisements().add(advertisement);
        ownersDao.saveAndFlush(owner);
    }

    @DeleteMapping("/{id}/advertisement/{adId}")
    public String delete(@PathVariable("id") Long id, @PathVariable("adId") Long id2) {
        Owner owner = ownersDao.getById(id);
        Advertisement advertisement = owner.getAdvertisements().stream().filter(adv -> adv.getId().equals(id2))
                .findFirst().orElse(null);
        if (advertisement == null) {
            throw new NotFoundException();
        }
        owner.getAdvertisements().remove(advertisement);
        ownersDao.saveAndFlush(owner);
        log.info("Deleted advertisement with id: " + id2);
        return "Deleted advertisement with id: " + id2;
    }
}
