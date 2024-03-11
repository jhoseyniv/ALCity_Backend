package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevelObjective;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelObjectiveRepository extends CrudRepository<PuzzleLevelObjective,Long> {
    Optional<PuzzleLevelObjective> findById(Long id);
    Collection<PuzzleLevelObjective> findAll();
    Collection<PuzzleLevelObjective> findByTitle(String title);
    Collection<PuzzleLevelObjective> findByCondition(String condition);

}
