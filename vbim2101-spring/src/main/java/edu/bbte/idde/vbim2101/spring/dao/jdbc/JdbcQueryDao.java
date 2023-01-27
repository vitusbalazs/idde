package edu.bbte.idde.vbim2101.spring.dao.jdbc;

import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Queries;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcQueryDao implements QueryDao {
    @Override
    public Collection<Queries> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Queries getById(Long id) {
        return null;
    }

    @Override
    public Queries saveAndFlush(Queries entity) {
        return null;
    }

    @Override
    public void delete(Queries entity) {

    }

    @Override
    public Collection<Queries> findBetweenTimestamps(Timestamp mini, Timestamp maxi) {
        return new ArrayList<>();
    }
}
