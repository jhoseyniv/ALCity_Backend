package com.alcity.repository.base;

import com.alcity.entity.base.DataType;
import com.alcity.entity.base.LearningSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningSkillRepository extends CrudRepository<LearningSkill,Long> {
    Optional<LearningSkill> findById(Long id);
    Collection<LearningSkill> findAll();
    LearningSkill findByLabel(String label);
    LearningSkill findByValue(String value);
}
