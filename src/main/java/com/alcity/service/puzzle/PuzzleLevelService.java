package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.puzzle.PuzzleLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelService implements PuzzleLevelRepository {


    @Autowired
    PuzzleLevelRepository puzzleLevelRepository;
    @Override
    public <S extends PuzzleLevel> S save(S entity) {
        return puzzleLevelRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevel> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevel> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<PuzzleLevel> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevel> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevel entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevel> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
