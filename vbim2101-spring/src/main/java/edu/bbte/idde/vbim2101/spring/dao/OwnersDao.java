package edu.bbte.idde.vbim2101.spring.dao;

import edu.bbte.idde.vbim2101.spring.model.Owner;

import java.util.Collection;

public interface OwnersDao extends Dao<Owner> {
    Collection<Owner> findByAge(Integer age);
}
