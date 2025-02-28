package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.LearningTopicInPL;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLLearningTopicRepository extends CrudRepository<LearningTopicInPL,Long> {

    Optional<LearningTopicInPL> findById(Long id);
    Collection<LearningTopicInPL> findAll();

}
