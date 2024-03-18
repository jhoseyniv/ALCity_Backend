package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleLevelRule;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRuleRepository extends CrudRepository<PuzzleLevelRule,Long> {
    Optional<PuzzleLevelRule> findById(Long id);
    Collection<PuzzleLevelRule> findAll();
    Collection<PuzzleLevelRule> findByTitle(String title);
    Collection<PuzzleLevelRule> findByCondition(String condition);

}
