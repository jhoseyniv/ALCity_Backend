package com.alcity.repository.puzzle;

import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleLevelGameInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelGameInstanceRepository extends CrudRepository<PuzzleLevelGameInstance,Long> {
    Optional<PuzzleLevelGameInstance> findById(Long id);
    Collection<PuzzleLevelGameInstance> findAll();

}
