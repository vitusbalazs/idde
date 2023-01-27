package edu.bbte.idde.vbim2101.spring.dao.jdbc;

import edu.bbte.idde.vbim2101.spring.dao.QueryDao;
import edu.bbte.idde.vbim2101.spring.model.Query;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;

public class JdbcQueryDao implements QueryDao {
    @Override
    public Collection<Query> findAll() {
        return new ArrayList<>();
    }

    @Override
    public Query getById(Long id) {
        return null;
    }

    @Override
    public Query saveAndFlush(Query entity) {
        return null;
    }

    @Override
    public void delete(Query entity) {

    }

    @Override
    public Collection<Query> findBetweenTimestamps(Timestamp mini, Timestamp maxi) {
        return new ArrayList<>();
    }
}
