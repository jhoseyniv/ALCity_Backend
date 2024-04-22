package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel_LearningTopic;
import com.alcity.repository.puzzle.PLLearningTopicRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLLearningTopicService implements PLLearningTopicRepository {

    @Autowired
    PLLearningTopicRepository puzzleLevelLearningTopicRepository;
    @Override
    public <S extends PuzzleLevel_LearningTopic> S save(S entity) {
        return puzzleLevelLearningTopicRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevel_LearningTopic> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevel_LearningTopic> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevel_LearningTopic> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevel_LearningTopic> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevel_LearningTopic entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevel_LearningTopic> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
