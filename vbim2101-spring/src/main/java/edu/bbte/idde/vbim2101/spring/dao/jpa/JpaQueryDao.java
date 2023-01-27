package edu.bbte.idde.vbim2101.spring.dao.jpa;

import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Query;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaQueryDao extends QueryDao, JpaRepository<Query, Long> {
}
