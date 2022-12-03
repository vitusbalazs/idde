package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.model.Advertisement;

import java.util.Collection;

public interface AdvertisementsDao extends Dao<Advertisement> {
    Collection<Advertisement> findByRooms(Integer age);
}
