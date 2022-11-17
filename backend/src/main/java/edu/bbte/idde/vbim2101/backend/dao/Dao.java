package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    Collection<T> findAll();

    T findById(Long id);

    void create(T entity);

    void update(Long id, T entity);

    void delete(Long id);

    Collection<T> findByAge(Integer age);
}
