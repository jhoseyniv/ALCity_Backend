package com.alcity.repository.alobject;

import com.alcity.entity.alenum.POActionOwnerType;
import com.alcity.entity.alobject.ObjectAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ActionRepository extends CrudRepository<ObjectAction,Long> {
    Optional<ObjectAction> findById(Long id);
    Collection<ObjectAction> findAll();
    Collection<ObjectAction> findByActionRendererId(Long id);
    Collection<ObjectAction> findByPoActionOwnerType(Long id);
    Collection<ObjectAction> findByOwnerObjectid(Long ownerId);
    Collection<ObjectAction> findByOwnerObjectidAndPoActionOwnerType(Long ownerId, POActionOwnerType ownerType);

}
