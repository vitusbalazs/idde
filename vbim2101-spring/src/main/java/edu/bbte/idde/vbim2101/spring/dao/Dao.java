package edu.bbte.idde.vbim2101.spring.dao;

import edu.bbte.idde.vbim2101.spring.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    Collection<T> findAll();

    T getById(Long id);

    T saveAndFlush(T entity);

//    Boolean update(Long id, T entity);

//    Boolean delete(Long id);
}
