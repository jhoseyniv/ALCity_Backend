package com.alcity.repository.alobject;

import com.alcity.entity.alobject.PuzzleObject_ObjectAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleObject_ObjectActionRepository extends CrudRepository<PuzzleObject_ObjectAction,Long> {
    Optional<PuzzleObject_ObjectAction> findById(Long id);
    Collection<PuzzleObject_ObjectAction> findAll();
    Collection<PuzzleObject_ObjectAction> findByActionRendererId(Long id);
    Collection<PuzzleObject_ObjectAction> findByPuzzleObjectActionOwnerType(Long id);

}
