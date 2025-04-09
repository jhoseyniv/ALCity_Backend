package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PGLearningSkillContent;
import com.alcity.repository.puzzle.PGSkillLearningContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PGSkillLearningContentService implements PGSkillLearningContentRepository {

    @Autowired
    PGSkillLearningContentRepository puzzleSkillLearningContentRepository;

    @Override
    public <S extends PGLearningSkillContent> S save(S entity) {
        return puzzleSkillLearningContentRepository.save(entity);
    }

    @Override
    public <S extends PGLearningSkillContent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PGLearningSkillContent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PGLearningSkillContent> findAll() {
        return null;
    }

    @Override
    public Iterable<PGLearningSkillContent> findAllById(Iterable<Long> longs) {
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
    public void delete(PGLearningSkillContent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PGLearningSkillContent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
