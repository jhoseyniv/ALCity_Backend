package com.alcity.repository.alobject;

import com.alcity.entity.alenum.ObjectActionType;
import com.alcity.entity.alobject.Renderer;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface RendererRepository extends CrudRepository<Renderer,Long> {
    Optional<Renderer> findById(Long id);
    Collection<Renderer> findAll();
    Optional<Renderer> findByHandlerAndObjectAction(String handler, ObjectActionType action);
    Collection<Renderer> findByClientTypeId(Long id);

}
