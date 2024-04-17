package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleGroupObjectInstance;
import com.alcity.entity.puzzle.PuzzleLevelObjective;
import com.alcity.repository.puzzle.PuzzleGroupObjectInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleGroupObjectInstanceService implements PuzzleGroupObjectInstanceRepository {

    @Autowired
    PuzzleGroupObjectInstanceRepository puzzleGroupObjectInstanceRepository;

    @Override
    public <S extends PuzzleGroupObjectInstance> S save(S entity) {
        return puzzleGroupObjectInstanceRepository.save(entity);
    }

    @Override
    public <S extends PuzzleGroupObjectInstance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleGroupObjectInstance> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleGroupObjectInstance> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleGroupObjectInstance> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleGroupObjectInstance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleGroupObjectInstance> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleGroupObjectInstance> findByRow(Long row) {
        return null;
    }

    @Override
    public Collection<PuzzleGroupObjectInstance> findByCol(String col) {
        return null;
    }

    @Override
    public Collection<PuzzleGroupObjectInstance> findByzOrder(String zOrder) {
        return null;
    }

    @Override
    public Collection<PuzzleGroupObjectInstance> findByPuzzleLevel(PuzzleLevelObjective pl) {
        return puzzleGroupObjectInstanceRepository.findByPuzzleLevel(pl);
    }
}
