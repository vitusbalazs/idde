package edu.bbte.idde.vbim2101.spring.dao.jpa;

import edu.bbte.idde.vbim2101.spring.dao.OwnersDao;
import edu.bbte.idde.vbim2101.spring.model.Owner;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Profile("jpa")
public interface JpaOwnersDao extends OwnersDao, JpaRepository<Owner, Long> {
}
