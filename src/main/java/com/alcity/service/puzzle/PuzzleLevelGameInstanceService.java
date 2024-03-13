package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PuzzleLevelGameInstance;
import com.alcity.repository.base.GameStatusRepository;
import com.alcity.repository.puzzle.PuzzleLevelGameInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelGameInstanceService implements PuzzleLevelGameInstanceRepository {

    @Autowired
    PuzzleLevelGameInstanceRepository puzzleLevelGameInstanceRepository;


    @Override
    public <S extends PuzzleLevelGameInstance> S save(S entity) {
        return puzzleLevelGameInstanceRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelGameInstance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelGameInstance> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelGameInstance> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelGameInstance> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelGameInstance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelGameInstance> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
