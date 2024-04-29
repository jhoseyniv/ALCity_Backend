package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleCategoryRepository  extends CrudRepository<PuzzleCategory,Long> {
    Optional<PuzzleCategory> findById(Long id);
    Collection<PuzzleCategory> findAll();
    PuzzleCategory findByLabel(String label);
    PuzzleCategory findByValue(String value);
    Collection<PuzzleCategory> findByValueContains(String criteria);

}
