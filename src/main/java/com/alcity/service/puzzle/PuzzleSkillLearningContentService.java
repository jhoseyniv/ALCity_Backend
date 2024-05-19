package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.LearningSkillContent;
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
    public <S extends LearningSkillContent> S save(S entity) {
        return puzzleSkillLearningContentRepository.save(entity);
    }

    @Override
    public <S extends LearningSkillContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<LearningSkillContent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<LearningSkillContent> findAll() {
        return null;
    }

    @Override
    public Iterable<LearningSkillContent> findAllById(Iterable<Long> longs) {
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
    public void delete(LearningSkillContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends LearningSkillContent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
