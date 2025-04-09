package com.alcity.repository.puzzle;

import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.LearningTopicInPL;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLLearningTopicRepository extends CrudRepository<LearningTopicInPL,Long> {

    Optional<LearningTopicInPL> findById(Long id);
    Optional<LearningTopicInPL> findByLearningTopicAndAndLearningContent(LearningTopic learningTopic, LearningContent learningContent);
    Collection<LearningTopicInPL> findAll();

}
