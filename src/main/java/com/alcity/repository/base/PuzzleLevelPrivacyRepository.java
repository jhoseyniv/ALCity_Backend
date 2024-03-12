package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleLevelDifficulty;
import com.alcity.entity.base.PuzzleLevelPrivacy;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelPrivacyRepository extends CrudRepository<PuzzleLevelPrivacy,Long> {
    Optional<PuzzleLevelPrivacy> findById(Long id);
    Collection<PuzzleLevelPrivacy> findAll();
    PuzzleLevelPrivacy findByLabel(String label);
    PuzzleLevelPrivacy findByValue(String value);

}
