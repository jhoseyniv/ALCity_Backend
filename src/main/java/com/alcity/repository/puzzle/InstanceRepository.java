package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.Instance;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface InstanceRepository extends CrudRepository<Instance,Long> {
    Optional<Instance> findById(Long id);
    Collection<Instance> findAll();
    Collection<Instance> findByRow(Integer row);
    Collection<Instance> findByCol(Integer col);
    Collection<Instance> findByzOrder(Integer zOrder);
    Collection<Instance> findByAlCityObjectInPGAndPuzzleLevel(ALCityObjectInPG pgObject, PuzzleLevel pl);
}
