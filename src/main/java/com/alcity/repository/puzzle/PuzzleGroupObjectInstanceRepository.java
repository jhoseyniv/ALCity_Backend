package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PGObjectInstance;
import com.alcity.entity.puzzle.PLObjective;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleGroupObjectInstanceRepository extends CrudRepository<PGObjectInstance,Long> {
    Optional<PGObjectInstance> findById(Long id);
    Collection<PGObjectInstance> findAll();
    Collection<PGObjectInstance> findByRow(Long row);
    Collection<PGObjectInstance> findByCol(String col);
    Collection<PGObjectInstance> findByzOrder(String zOrder);
    Collection<PGObjectInstance> findByPuzzleLevel(PLObjective pl);

}
