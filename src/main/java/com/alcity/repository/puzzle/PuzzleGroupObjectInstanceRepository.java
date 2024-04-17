package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleGroupObjectInstance;
import com.alcity.entity.puzzle.PuzzleLevelObjective;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleGroupObjectInstanceRepository extends CrudRepository<PuzzleGroupObjectInstance,Long> {
    Optional<PuzzleGroupObjectInstance> findById(Long id);
    Collection<PuzzleGroupObjectInstance> findAll();
    Collection<PuzzleGroupObjectInstance> findByRow(Long row);
    Collection<PuzzleGroupObjectInstance> findByCol(String col);
    Collection<PuzzleGroupObjectInstance> findByzOrder(String zOrder);
    Collection<PuzzleGroupObjectInstance> findByPuzzleLevel(PuzzleLevelObjective pl);

}
