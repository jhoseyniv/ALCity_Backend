package com.alcity.repository.learning;

import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningSkill_LearningTopic;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningSkill_LearningTopicRepository  extends CrudRepository<LearningSkill_LearningTopic,Long> {
    Optional<LearningSkill_LearningTopic> findById(Long id);
    Collection<LearningSkill_LearningTopic> findAll();
    LearningSkill_LearningTopic findByTitle(String title);

}
