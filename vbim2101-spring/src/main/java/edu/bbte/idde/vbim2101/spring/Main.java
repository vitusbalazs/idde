package edu.bbte.idde.vbim2101.spring;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan
@SpringBootApplication
@Slf4j
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Autowired
    private AdvertisementsDao advertisementsDao;

    public CommandLineRunner runner() {
        return args -> {
            log.info("Hello");
            Advertisement newAdvertisement = new Advertisement("Szia", "Szia2", 1, 2, 3, 1L);
            advertisementsDao.create(newAdvertisement);

            log.info(advertisementsDao.findAll().toString());
        };
    }
}
