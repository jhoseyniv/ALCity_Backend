package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleObject;
import com.alcity.repository.puzzle.PuzzleObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleObjectService implements PuzzleObjectRepository {

    @Autowired
    PuzzleObjectRepository puzzleObjectRepository;

    @Override
    public <S extends PuzzleObject> S save(S entity) {
        return null;
    }

    @Override
    public <S extends PuzzleObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleObject> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleObject> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleObject> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleObject entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleObject> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleObject> findByTitle(String title) {
        return null;
    }
}
