package edu.bbte.idde.vbim2101.spring.dao;

import edu.bbte.idde.vbim2101.spring.model.Advertisement;

import java.util.Collection;

public interface AdvertisementsDao extends Dao<Advertisement> {
    Collection<Advertisement> findByRooms(Integer age);
}

