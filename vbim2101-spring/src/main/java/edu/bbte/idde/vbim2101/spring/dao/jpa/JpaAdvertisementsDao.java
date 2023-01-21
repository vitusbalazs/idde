package edu.bbte.idde.vbim2101.spring.dao.jpa;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface JpaAdvertisementsDao extends AdvertisementsDao, JpaRepository<Advertisement, Long> {
}
