package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ObjectActionEvent;
import com.alcity.entity.puzzle.PuzzleLevelRuleEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;


public interface ObjectActionEventRepository extends CrudRepository<ObjectActionEvent,Long> {
    Optional<ObjectActionEvent> findById(Long id);
    Collection<ObjectActionEvent> findAll();

}
