package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevelLearningTopic;
import com.alcity.entity.puzzle.PuzzleLevelObjective;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelLearningTopicRepository extends CrudRepository<PuzzleLevelLearningTopic,Long> {

    Optional<PuzzleLevelLearningTopic> findById(Long id);
    Collection<PuzzleLevelLearningTopic> findAll();

}
