package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PLGameInstance;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelGameInstanceRepository extends CrudRepository<PLGameInstance,Long> {
    Optional<PLGameInstance> findById(Long id);
    Collection<PLGameInstance> findAll();

}
