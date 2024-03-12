package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleLevelLearningTopic;
import com.alcity.repository.puzzle.PuzzleLevelLearningTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelLearningTopicService implements PuzzleLevelLearningTopicRepository {

    @Autowired
    PuzzleLevelLearningTopicRepository puzzleLevelLearningTopicRepository;
    @Override
    public <S extends PuzzleLevelLearningTopic> S save(S entity) {
        return puzzleLevelLearningTopicRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelLearningTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelLearningTopic> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelLearningTopic> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelLearningTopic> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelLearningTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelLearningTopic> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
