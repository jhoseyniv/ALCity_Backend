package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.LearningSubjectInPL;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLLearningTopicRepository extends CrudRepository<LearningSubjectInPL,Long> {

    Optional<LearningSubjectInPL> findById(Long id);
    Collection<LearningSubjectInPL> findAll();

}
