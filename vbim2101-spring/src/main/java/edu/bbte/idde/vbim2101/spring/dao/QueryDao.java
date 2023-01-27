package edu.bbte.idde.vbim2101.spring.dao;

import edu.bbte.idde.vbim2101.spring.model.Query;

import java.sql.Timestamp;
import java.util.Collection;

public interface QueryDao extends Dao<Query> {
    Collection<Query> findBetweenTimestamps(Timestamp mini, Timestamp maxi);
}
