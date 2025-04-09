package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PGLearningSkillContent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PGSkillLearningContentRepository extends CrudRepository<PGLearningSkillContent,Long> {
    Optional<PGLearningSkillContent> findById(Long id);
    Collection<PGLearningSkillContent> findAll();

}
