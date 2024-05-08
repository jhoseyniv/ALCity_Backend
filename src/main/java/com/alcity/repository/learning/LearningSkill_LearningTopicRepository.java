package com.alcity.repository.learning;

import com.alcity.entity.learning.LearningSkillTopic;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningSkill_LearningTopicRepository  extends CrudRepository<LearningSkillTopic,Long> {
    Optional<LearningSkillTopic> findById(Long id);
    Collection<LearningSkillTopic> findAll();
    LearningSkillTopic findByTitle(String title);

}
