package edu.bbte.idde.vbim2101.backend.dao;

import edu.bbte.idde.vbim2101.backend.model.Owner;

import java.util.Collection;

public interface OwnersDao extends Dao<Owner> {
    Collection<Owner> findByAge(Integer age);
}
