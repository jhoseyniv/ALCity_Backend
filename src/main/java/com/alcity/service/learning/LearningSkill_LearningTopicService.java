package com.alcity.service.learning;

import com.alcity.entity.learning.LearningSkill_LearningTopic;
import com.alcity.repository.learning.LearningSkill_LearningTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningSkill_LearningTopicService implements LearningSkill_LearningTopicRepository {

    @Autowired
    LearningSkill_LearningTopicRepository learningSkill_LearningTopicRepository;

    @Override
    public <S extends LearningSkill_LearningTopic> S save(S entity) {
        return learningSkill_LearningTopicRepository.save(entity);
    }

    @Override
    public <S extends LearningSkill_LearningTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkill_LearningTopic> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningSkill_LearningTopic> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningSkill_LearningTopic> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningSkill_LearningTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkill_LearningTopic> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public LearningSkill_LearningTopic findByTitle(String title) {
        return learningSkill_LearningTopicRepository.findByTitle(title);
    }
}
