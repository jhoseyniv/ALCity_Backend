package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleLevelGround;
import com.alcity.repository.puzzle.PuzzleLevelGroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelGroundService implements PuzzleLevelGroundRepository {

    @Autowired
    PuzzleLevelGroundRepository puzzleLevelGroundRepository;
    @Override
    public <S extends PuzzleLevelGround> S save(S entity) {
        return puzzleLevelGroundRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelGround> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelGround> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelGround> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelGround> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelGround entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelGround> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleLevelGround> findByNumColumns(Integer cols) {
        return null;
    }

    @Override
    public Collection<PuzzleLevelGround> findByNumRows(Integer rows) {
        return null;
    }
}
