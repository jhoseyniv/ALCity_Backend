package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.base.ClientType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ObjectActionRepository extends CrudRepository<ObjectAction,Long> {
    Optional<ObjectAction> findById(Long id);
    Collection<ObjectAction> findAll();
    ObjectAction findByLabel(String label);
    ObjectAction findByValue(String value);

}
