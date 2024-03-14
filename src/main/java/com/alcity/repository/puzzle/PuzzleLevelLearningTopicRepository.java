package com.alcity.repository.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel_LearningTopic;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PuzzleLevelLearningTopicRepository extends CrudRepository<PuzzleLevel_LearningTopic,Long> {

    Optional<PuzzleLevel_LearningTopic> findById(Long id);
    Collection<PuzzleLevel_LearningTopic> findAll();

}
