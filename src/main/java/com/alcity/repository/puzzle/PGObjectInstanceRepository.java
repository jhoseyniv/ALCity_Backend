package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PGObjectInstance;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleLevel;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PGObjectInstanceRepository extends CrudRepository<PGObjectInstance,Long> {
    Optional<PGObjectInstance> findById(Long id);
    Collection<PGObjectInstance> findAll();
    Collection<PGObjectInstance> findByRow(Integer row);
    Collection<PGObjectInstance> findByCol(Integer col);
    Collection<PGObjectInstance> findByzOrder(Integer zOrder);
    Collection<PGObjectInstance> findByPuzzleLevel(PuzzleLevel pl);

}
