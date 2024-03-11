package com.alcity.repository.journey;

import com.alcity.entity.journey.JourneyLearningSkill;
import com.alcity.entity.puzzle.PuzzleGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface JourneyLearningSkillRepository extends CrudRepository<JourneyLearningSkill,Long> {
    Optional<JourneyLearningSkill> findById(Long id);
    Collection<JourneyLearningSkill> findAll();
    Collection<JourneyLearningSkill> findByRequiredAmount(Float amount);
}
