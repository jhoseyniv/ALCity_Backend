package com.alcity.repository.learning;

import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningTopicRepository extends CrudRepository<LearningTopic,Long> {
    Optional<LearningTopic> findById(Long id);
    Collection<LearningTopic> findAll();
    Collection<LearningTopic> findByTitle(String title);
}
