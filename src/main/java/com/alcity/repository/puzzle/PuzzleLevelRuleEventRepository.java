package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevelRule;
import com.alcity.entity.puzzle.PuzzleLevelRuleEvent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRuleEventRepository extends CrudRepository<PuzzleLevelRuleEvent,Long> {
    Optional<PuzzleLevelRuleEvent> findById(Long id);
    Collection<PuzzleLevelRuleEvent> findAll();
    Collection<PuzzleLevelRuleEvent> findByName(String name);

}
