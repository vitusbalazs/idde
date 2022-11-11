package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.model.BaseEntity;

import java.util.Collection;

public interface Dao<T extends BaseEntity> {
    Collection<T> findAllAdvertisements();

    T findById(Long id);

    void createAdvertisement(T entity);

    void updateAdvertisement(Long id, T entity);

    void deleteAdvertisement(Long id);
}
