package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import edu.bbte.idde.vbim2101.spring.model.AdvertisementsMapper;
import edu.bbte.idde.vbim2101.spring.model.dto.AdvertisementInDto;
import edu.bbte.idde.vbim2101.spring.model.dto.AdvertisementOutDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@RestController
@RequestMapping("/advertisements")
@Slf4j
public class AdvertisementsController {
    @Autowired
    private AdvertisementsDao advertisementsDao;
    @Autowired
    private AdvertisementsMapper advertisementsMapper;

    @GetMapping
    public Collection<AdvertisementOutDto> findAll(@RequestParam(required = false) Integer rooms) {
        if (rooms == null) {
            return advertisementsMapper.dtosFromAdvertisements(advertisementsDao.findAll());
        }
        return advertisementsMapper.dtosFromAdvertisements(advertisementsDao.findByRooms(rooms));
    }

    @GetMapping("/{id}")
    public AdvertisementOutDto findById(@PathVariable("id") Long id) {
        Advertisement result = advertisementsDao.findById(id);
        if (result == null) {
            throw new NotFoundException();
        }
        return advertisementsMapper.dtoFromAdvertisement(result);
    }

    @PostMapping
    public void create(@RequestBody @Valid AdvertisementInDto advertisementInDto) {
        advertisementsDao.create(advertisementsMapper.advertisementFromDto(advertisementInDto));
    }

    @PutMapping
    public void update(@RequestParam(required = true) Long id, @RequestBody @Valid AdvertisementInDto advertisementInDto) {
        Advertisement advertisement = advertisementsMapper.advertisementFromDto(advertisementInDto);
        advertisement.setId(id);
        advertisementsDao.update(id, advertisement);
    }

    @DeleteMapping
    public void delete(@RequestParam(required = true) Long id) {
        advertisementsDao.delete(id);
    }
}
