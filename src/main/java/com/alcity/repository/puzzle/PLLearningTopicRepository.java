package com.alcity.repository.puzzle;

import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.PLLearningTopic;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface PLLearningTopicRepository extends CrudRepository<PLLearningTopic,Long> {

    Optional<PLLearningTopic> findById(Long id);
    Optional<PLLearningTopic> findByLearningTopicAndAndLearningContent(LearningTopic learningTopic, LearningContent learningContent);
    Collection<PLLearningTopic> findAll();

}
