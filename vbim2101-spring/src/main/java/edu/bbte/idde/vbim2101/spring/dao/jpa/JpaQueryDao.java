package edu.bbte.idde.vbim2101.spring.dao.jpa;

import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Queries;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.util.Collection;

public interface JpaQueryDao extends QueryDao, JpaRepository<Queries, Long> {
    @Query("select a from Queries a where a >= mini and a <= maxi")
    @Transactional
    @Modifying
    @Override
    Collection<Queries> findBetweenTimestamps(@Param("mini") Timestamp mini, @Param("maxi") Timestamp maxi);
}
