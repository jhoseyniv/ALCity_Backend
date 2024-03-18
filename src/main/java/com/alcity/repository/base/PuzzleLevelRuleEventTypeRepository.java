package com.alcity.repository.base;

import com.alcity.entity.base.PuzzleLevelDifficulty;
import com.alcity.entity.base.PuzzleLevelRuleEventType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRuleEventTypeRepository extends CrudRepository<PuzzleLevelRuleEventType,Long> {
    Optional<PuzzleLevelRuleEventType> findById(Long id);
    Collection<PuzzleLevelRuleEventType> findAll();
    PuzzleLevelRuleEventType findByLabel(String label);
    PuzzleLevelRuleEventType findByValue(String value);

}
