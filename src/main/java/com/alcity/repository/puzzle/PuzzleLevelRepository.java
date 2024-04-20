package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRepository  extends CrudRepository<PuzzleLevel,Long> {
    Optional<PuzzleLevel> findById(Long id);
    Collection<PuzzleLevel> findAll();
    Collection<PuzzleLevel> findByName(String title);
    Collection<PuzzleLevel> findByCode(String code);

}
