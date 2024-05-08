package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.LearningSubjectInPL;
import com.alcity.repository.puzzle.PLLearningTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLLearningTopicService implements PLLearningTopicRepository {

    @Autowired
    @Qualifier("PLLearningTopicRepository")

    PLLearningTopicRepository plLearningTopicRepository;
    @Override
    public <S extends LearningSubjectInPL> S save(S entity) {
        return plLearningTopicRepository.save(entity);
    }

    @Override
    public <S extends LearningSubjectInPL> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSubjectInPL> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningSubjectInPL> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningSubjectInPL> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningSubjectInPL entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSubjectInPL> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
