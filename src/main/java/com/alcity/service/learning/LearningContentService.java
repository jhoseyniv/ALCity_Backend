package com.alcity.service.learning;

import com.alcity.entity.learning.LearningContent;
import com.alcity.repository.learning.LearningContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class LearningContentService implements LearningContentRepository {

    @Autowired
    LearningContentRepository learningContentRepository;

    @Override
    public <S extends LearningContent> S save(S entity) {
        return learningContentRepository.save(entity);
    }

    @Override
    public <S extends LearningContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningContent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningContent> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningContent> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningContent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<LearningContent> findByDescText(String desc) {
        return null;
    }

    @Override
    public Collection<LearningContent> findByDescBrief(String brief) {
        return null;
    }
}