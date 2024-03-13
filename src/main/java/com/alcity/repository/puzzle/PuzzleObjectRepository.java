package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.puzzle.PuzzleObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleObjectRepository extends CrudRepository<PuzzleObject,Long> {
    Optional<PuzzleObject> findById(Long id);
    Collection<PuzzleObject> findAll();

    Collection<PuzzleObject> findByTitle(String title);

}
