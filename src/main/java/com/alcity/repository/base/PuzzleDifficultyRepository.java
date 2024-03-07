package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.base.PuzzleDifficulty;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleDifficultyRepository  extends CrudRepository<PuzzleDifficulty,Long> {

    Optional<PuzzleDifficulty> findById(Long id);
    Collection<PuzzleDifficulty> findAll();
    PuzzleDifficulty findByLabel(String label);
    PuzzleDifficulty findByValue(String value);

}
