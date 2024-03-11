package com.alcity.repository.learning;

import com.alcity.entity.learning.LearningContent;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface LearningContentRepository extends CrudRepository<LearningContent,Long> {
    Optional<LearningContent> findById(Long id);
    Collection<LearningContent> findAll();
    Collection<LearningContent>  findByDescText(String desc);
    Collection<LearningContent>  findByDescBrief(String brief);
}
