package edu.bbte.idde.vbim2101.spring.dao.jpa;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Profile("jpa")
public interface JpaOwnersDao extends OwnersDao, JpaRepository<Owner, Long> {
    @Modifying
    @Query("select o from Owner o where o.age=?1")
    @Transactional
    Collection<Owner> findByAge(Integer age);


}
