package edu.bbte.idde.vbim2101.spring.dao;

import edu.bbte.idde.vbim2101.spring.model.Queries;

import java.sql.Timestamp;
import java.util.Collection;

public interface QueryDao extends Dao<Queries> {
    Collection<Queries> findBetweenTimestamps(Timestamp mini, Timestamp maxi);
}
