package com.alcity.service.learning;

import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.repository.learning.LearningSkillRepository;
import com.alcity.repository.learning.LearningTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningTopicService implements LearningTopicRepository {

   @Autowired
    LearningTopicRepository learningTopicRepository;

    @Override
    public <S extends LearningTopic> S save(S entity) {
        return learningTopicRepository.save(entity);
    }

    @Override
    public <S extends LearningTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningTopic> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningTopic> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningTopic> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningTopic> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<LearningTopic> findByTitle(String title) {
        return null;
    }
}
