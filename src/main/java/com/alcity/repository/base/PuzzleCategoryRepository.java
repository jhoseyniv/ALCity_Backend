package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleCategory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.ResponseEntity;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleCategoryRepository  extends CrudRepository<PuzzleCategory,Long> {
    Optional<PuzzleCategory> findById(Long id);
    Collection<PuzzleCategory> findAll(Specification specification);
    Collection<PuzzleCategory> findAll();
    PuzzleCategory findByLabel(String label);
    PuzzleCategory findByValue(String value);
    Collection<PuzzleCategory> findByValueContains(String criteria);
    void deleteById(Long id);

}
