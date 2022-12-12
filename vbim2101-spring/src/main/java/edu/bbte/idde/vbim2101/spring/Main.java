package edu.bbte.idde.vbim2101.spring;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@Slf4j
public class Main {
    @Autowired
    private AdvertisementsDao advertisementsDao;
    @Autowired
    private OwnersDao ownersDao;

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Bean
    public CommandLineRunner runner() {
        return args -> {
            log.info("Hello from command line runner");
            Advertisement newAdvertisement = new Advertisement("Szia", "Szia2", 1, 2, 3, 1L);
            advertisementsDao.create(newAdvertisement);
            newAdvertisement = new Advertisement("Szia", "Szia2", 1, 2, 3, 1L);
            advertisementsDao.create(newAdvertisement);
            Owner newOwner = new Owner("Ferenc", "ferenc@email.com", 34);
            ownersDao.create(newOwner);
            newOwner = new Owner("Jozsef", "jozsef@email.com", 33);
            ownersDao.create(newOwner);
            // log.info(advertisementsDao.findById(1L).toString());
            // log.info(advertisementsDao.findAll().toString());
        };
    }
}
