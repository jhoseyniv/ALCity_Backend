package com.alcity.service.base;


import com.alcity.entity.base.PuzzleDifficulty;
import com.alcity.repository.base.PuzzleCategoryRepository;
import com.alcity.repository.base.PuzzleDifficultyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleDifficultyService implements PuzzleDifficultyRepository {

    @Autowired
    PuzzleDifficultyRepository puzzleDifficultyRepository;

    @Override
    public <S extends PuzzleDifficulty> S save(S entity) {
        return puzzleDifficultyRepository.save(entity);
    }

    @Override
    public <S extends PuzzleDifficulty> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleDifficulty> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleDifficulty> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleDifficulty> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleDifficulty entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleDifficulty> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleDifficulty findByLabel(String label) {
        return null;
    }

    @Override
    public PuzzleDifficulty findByValue(String value) {
        return null;
    }
}
