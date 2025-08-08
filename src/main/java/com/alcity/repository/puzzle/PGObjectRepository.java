package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.BaseObject;
import com.alcity.entity.puzzle.PGObject;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PGObjectRepository extends CrudRepository<PGObject,Long> {
    Optional<PGObject> findById(Long id);
    Collection<PGObject> findAll();

    Collection<PGObject> findByTitle(String title);
    Collection<PGObject> findByCode(String code);
    Optional<PGObject> findByCodeAndTitle(String code, String title);
    Collection<PGObject> findByalCityObject(BaseObject cityObject);
    Collection<PGObject> findByPuzzleGroup(PuzzleGroup puzzleGroup);

}
