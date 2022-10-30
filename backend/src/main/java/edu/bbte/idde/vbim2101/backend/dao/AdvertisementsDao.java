package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.model.Advertisement;

import java.util.Collection;

public interface AdvertisementsDao {
    Advertisement findById(Long id);

    Collection<Advertisement> findAllAdvertisements();

    void createAdvertisement(Advertisement advertisement);

    void updateAdvertisement(Long id, Advertisement advertisement);

    void deleteAdvertisement(Long id);
}
