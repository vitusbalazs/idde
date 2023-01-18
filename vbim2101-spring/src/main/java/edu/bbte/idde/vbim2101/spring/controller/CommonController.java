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

import java.util.ArrayList;
import java.util.Collection;

// findall advertisments for one owner, create, delete

// findall -> meglevo owner <- osszes advertisment
// create -> meglevo owner <- uj advertisementet
// delete -> meglevo owner <- kitorlom advertisment
@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private OwnersDao ownersDao;
    @Autowired
    private AdvertisementsMapper advertisementsMapper;

    @GetMapping("/{id}")
    public Collection<AdvertisementOutDto> findByOwner(@PathVariable("id") Long id) {
        Collection<Advertisement> advertisements = new ArrayList<>();
        for (Advertisement advertisement : ownersDao.getById(id).getAdvertisements()) {
            if (advertisement.getOwner().getId().equals(id)) {
                advertisements.add(advertisement);
            }
        }
        if (advertisements.isEmpty()) {
            throw new NotFoundException();
        }
        return advertisementsMapper.dtosFromAdvertisements(advertisements);
    }

    @PostMapping("/{id}")
    public void create(@PathVariable("id") Long id, @RequestBody AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        Owner owner = ownersDao.getById(id);
        advertisement.setOwner(owner);
        owner.getAdvertisements().add(advertisement);
        ownersDao.saveAndFlush(owner);
    }

    @DeleteMapping("/{id}/{adId}")
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
