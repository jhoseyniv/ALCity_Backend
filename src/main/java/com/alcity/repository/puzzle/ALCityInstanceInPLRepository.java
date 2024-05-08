package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.ALCityInstanceInPL;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ALCityInstanceInPLRepository extends CrudRepository<ALCityInstanceInPL,Long> {
    Optional<ALCityInstanceInPL> findById(Long id);
    Collection<ALCityInstanceInPL> findAll();
    Collection<ALCityInstanceInPL> findByRow(Integer row);
    Collection<ALCityInstanceInPL> findByCol(Integer col);
    Collection<ALCityInstanceInPL> findByzOrder(Integer zOrder);
    Collection<ALCityInstanceInPL> findByAlCityObjectInPGAndPuzzleLevel(ALCityObjectInPG pgObject, PuzzleLevel pl);
}
