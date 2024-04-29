package com.alcity.repository.learning;

import com.alcity.entity.learning.LearningSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningSkillRepository extends CrudRepository<LearningSkill,Long> {
    Optional<LearningSkill> findById(Long id);
    Collection<LearningSkill> findAll();
    Collection<LearningSkill> findByValueContains(String criteria);
    LearningSkill findByLabel(String label);
    LearningSkill findByValue(String value);

}
