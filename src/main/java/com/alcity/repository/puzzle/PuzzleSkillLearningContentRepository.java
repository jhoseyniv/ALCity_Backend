package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleSkillLearningContent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleSkillLearningContentRepository extends CrudRepository<PuzzleSkillLearningContent,Long> {
    Optional<PuzzleSkillLearningContent> findById(Long id);
    Collection<PuzzleSkillLearningContent> findAll();

}
