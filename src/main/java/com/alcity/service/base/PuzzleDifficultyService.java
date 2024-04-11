package com.alcity.service.base;


import com.alcity.entity.base.PuzzleLevelDifficulty;
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
    public <S extends PuzzleLevelDifficulty> S save(S entity) {
        return puzzleDifficultyRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelDifficulty> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelDifficulty> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelDifficulty> findAll() {

        return puzzleDifficultyRepository.findAll();
    }

    @Override
    public Iterable<PuzzleLevelDifficulty> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelDifficulty entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelDifficulty> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleLevelDifficulty findByLabel(String label) {
        return puzzleDifficultyRepository.findByLabel(label);
    }

    @Override
    public PuzzleLevelDifficulty findByValue(String value) {
        return puzzleDifficultyRepository.findByValue(value);
    }
}
