package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleLevelRulePostAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRulePostActionRepository extends CrudRepository<PuzzleLevelRulePostAction,Long> {
    Optional<PuzzleLevelRulePostAction> findById(Long id);
    Collection<PuzzleLevelRulePostAction> findAll();
    Collection<PuzzleLevelRulePostAction> findByOrdering(Integer ordering);
    Collection<PuzzleLevelRulePostAction> findByActionExpression(String experssion);
}
