package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.LearningSkillContent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleSkillLearningContentRepository extends CrudRepository<LearningSkillContent,Long> {
    Optional<LearningSkillContent> findById(Long id);
    Collection<LearningSkillContent> findAll();

}
