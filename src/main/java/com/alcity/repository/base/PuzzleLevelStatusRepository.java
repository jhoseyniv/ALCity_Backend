package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.base.PuzzleLevelStatus;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelStatusRepository extends CrudRepository<PuzzleLevelStatus,Long> {
    Optional<PuzzleLevelStatus> findById(Long id);
    Collection<PuzzleLevelStatus> findAll();
    PuzzleLevelStatus findByLabel(String label);
    PuzzleLevelStatus findByValue(String value);

}
