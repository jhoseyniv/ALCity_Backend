package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PGLearningSkill;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PGLearningSkillRepository extends CrudRepository<PGLearningSkill,Long> {
    Optional<PGLearningSkill> findById(Long id);
    Collection<PGLearningSkill> findAll();

}
