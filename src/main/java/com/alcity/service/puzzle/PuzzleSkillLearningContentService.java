package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleSkillLearningContent;
import com.alcity.repository.puzzle.PuzzleSkillLearningContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleSkillLearningContentService implements PuzzleSkillLearningContentRepository {

    @Autowired
    PuzzleSkillLearningContentRepository puzzleSkillLearningContentRepository;

    @Override
    public <S extends PuzzleSkillLearningContent> S save(S entity) {
        return puzzleSkillLearningContentRepository.save(entity);
    }

    @Override
    public <S extends PuzzleSkillLearningContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleSkillLearningContent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleSkillLearningContent> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleSkillLearningContent> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleSkillLearningContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleSkillLearningContent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
