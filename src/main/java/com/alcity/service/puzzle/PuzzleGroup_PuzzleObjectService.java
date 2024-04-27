package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup_PuzzleObject;
import com.alcity.repository.puzzle.PuzzleGroup_PuzzleObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleGroup_PuzzleObjectService implements PuzzleGroup_PuzzleObjectRepository {

    @Autowired
    PuzzleGroup_PuzzleObjectRepository puzzleGroup_PuzzleObjectRepository;

    @Override
    public <S extends PuzzleGroup_PuzzleObject> S save(S entity) {
        return puzzleGroup_PuzzleObjectRepository.save(entity);
    }

    @Override
    public <S extends PuzzleGroup_PuzzleObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleGroup_PuzzleObject> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleGroup_PuzzleObject> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleGroup_PuzzleObject> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleGroup_PuzzleObject entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleGroup_PuzzleObject> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleGroup_PuzzleObject> findByTitle(String title) {
        return null;
    }

    @Override
    public PuzzleGroup_PuzzleObject findByCode(String code) {
        return puzzleGroup_PuzzleObjectRepository.findByCode(code);
    }
}
