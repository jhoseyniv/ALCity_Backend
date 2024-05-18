package com.alcity.repository.alobject;

import com.alcity.entity.alenum.ObjectAction;
import com.alcity.entity.alobject.Renderer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ActionRendererRepository extends CrudRepository<Renderer,Long> {
    Optional<Renderer> findById(Long id);
    Collection<Renderer> findAll();
    Optional<Renderer> findByHandlerAndObjectAction(String handler, ObjectAction action);
    Collection<Renderer> findByClientTypeId(Long id);

}
