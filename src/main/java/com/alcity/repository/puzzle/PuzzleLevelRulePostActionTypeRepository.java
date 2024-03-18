package com.alcity.repository.puzzle;

import com.alcity.entity.base.DataType;
import com.alcity.entity.base.PuzzleLevelRulePostActionType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelRulePostActionTypeRepository extends CrudRepository<PuzzleLevelRulePostActionType,Long> {
    Optional<PuzzleLevelRulePostActionType> findById(Long id);
    Collection<PuzzleLevelRulePostActionType> findAll();
    PuzzleLevelRulePostActionType findByLabel(String label);
    PuzzleLevelRulePostActionType findByValue(String value);

}
