package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleLevelObjective;
import com.alcity.repository.puzzle.PuzzleLevelObjectiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleLevelObjectiveService implements PuzzleLevelObjectiveRepository {

    @Autowired
    PuzzleLevelObjectiveRepository puzzleLevelObjectiveRepository;

    @Override
    public <S extends PuzzleLevelObjective> S save(S entity) {
        return puzzleLevelObjectiveRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelObjective> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelObjective> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelObjective> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelObjective> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelObjective entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelObjective> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleLevelObjective> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<PuzzleLevelObjective> findByCondition(String condition) {
        return null;
    }
}
