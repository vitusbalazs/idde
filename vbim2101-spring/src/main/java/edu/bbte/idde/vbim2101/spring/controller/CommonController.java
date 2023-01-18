package edu.bbte.idde.vbim2101.spring.controller;

import edu.bbte.idde.vbim2101.spring.controller.mapper.AdvertisementsMapper;
import edu.bbte.idde.vbim2101.spring.controller.mapper.OwnersMapper;
import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/common")
public class CommonController {
    @Autowired
    private AdvertisementsDao advertisementsDao;
    @Autowired
    private AdvertisementsMapper advertisementsMapper;
    @Autowired
    private OwnersDao ownersDao;
    @Autowired
    private OwnersMapper ownersMapper;
}
