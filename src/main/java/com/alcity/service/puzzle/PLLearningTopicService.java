package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.LearningTopicInPL;
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
    public <S extends LearningTopicInPL> S save(S entity) {
        return plLearningTopicRepository.save(entity);
    }

    @Override
    public <S extends LearningTopicInPL> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningTopicInPL> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningTopicInPL> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningTopicInPL> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningTopicInPL entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningTopicInPL> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
