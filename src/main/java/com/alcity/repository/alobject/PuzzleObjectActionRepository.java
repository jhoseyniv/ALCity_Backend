package com.alcity.repository.alobject;

import com.alcity.entity.alobject.PuzzleObjectAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleObjectActionRepository extends CrudRepository<PuzzleObjectAction,Long> {
    Optional<PuzzleObjectAction> findById(Long id);
    Collection<PuzzleObjectAction> findAll();
    Collection<PuzzleObjectAction> findByActionRendererId(Long id);
    Collection<PuzzleObjectAction> findByPoActionOwnerType(Long id);
    Collection<PuzzleObjectAction> findByOwnerObjectid(Long id);

}
