package com.alcity.repository.alobject;

import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alobject.ActionRenderer;
import com.alcity.entity.alobject.ObjectCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ActionRendererRepository extends CrudRepository<ActionRenderer,Long> {
    Optional<ActionRenderer> findById(Long id);
    Collection<ActionRenderer> findAll();
    Optional<ActionRenderer> findByHandlerAndObjectAction(String handler, ObjectAction action);
    Collection<ActionRenderer> findByClientTypeId(Long id);

}
