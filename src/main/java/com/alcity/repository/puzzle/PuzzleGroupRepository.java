package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleGroupRepository extends CrudRepository<PuzzleGroup,Long> {
    Optional<PuzzleGroup> findById(Long id);
    Collection<PuzzleGroup> findAll();
    Collection<PuzzleGroup> findByTitle(String title);

}
