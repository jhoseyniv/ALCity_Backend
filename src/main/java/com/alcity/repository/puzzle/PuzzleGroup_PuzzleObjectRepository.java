package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup_PuzzleObject;
import com.alcity.entity.puzzle.PuzzleObject;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleGroup_PuzzleObjectRepository extends CrudRepository<PuzzleGroup_PuzzleObject,Long> {
    Optional<PuzzleGroup_PuzzleObject> findById(Long id);
    Collection<PuzzleGroup_PuzzleObject> findAll();

    Collection<PuzzleGroup_PuzzleObject> findByTitle(String title);
    Collection<PuzzleGroup_PuzzleObject> findByCode(String code);


}
