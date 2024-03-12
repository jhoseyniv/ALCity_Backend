package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleLevelDifficulty;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleDifficultyRepository  extends CrudRepository<PuzzleLevelDifficulty,Long> {

    Optional<PuzzleLevelDifficulty> findById(Long id);
    Collection<PuzzleLevelDifficulty> findAll();
    PuzzleLevelDifficulty findByLabel(String label);
    PuzzleLevelDifficulty findByValue(String value);

}
