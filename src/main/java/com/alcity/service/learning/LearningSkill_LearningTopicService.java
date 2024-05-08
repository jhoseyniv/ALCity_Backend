package com.alcity.service.learning;

import com.alcity.entity.learning.LearningSkillTopic;
import com.alcity.repository.learning.LearningSkill_LearningTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningSkill_LearningTopicService implements LearningSkill_LearningTopicRepository {

    @Autowired
    LearningSkill_LearningTopicRepository learningSkill_LearningTopicRepository;

    @Override
    public <S extends LearningSkillTopic> S save(S entity) {
        return learningSkill_LearningTopicRepository.save(entity);
    }

    @Override
    public <S extends LearningSkillTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkillTopic> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningSkillTopic> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningSkillTopic> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(LearningSkillTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkillTopic> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LearningSkillTopic findByTitle(String title) {
        return learningSkill_LearningTopicRepository.findByTitle(title);
    }
}
