package edu.bbte.idde.vbim2101.spring.dao.jpa;

import edu.bbte.idde.vbim2101.spring.dao.AdvertisementsDao;
import edu.bbte.idde.vbim2101.spring.model.Advertisement;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Profile("jpa")
public interface JpaAdvertisementsDao extends AdvertisementsDao, JpaRepository<Advertisement, Long> {
    @Modifying
    @Query("select a from Advertisement a where a.rooms=?1")
    @Transactional
    Collection<Advertisement> findByRooms(Integer age);
}
