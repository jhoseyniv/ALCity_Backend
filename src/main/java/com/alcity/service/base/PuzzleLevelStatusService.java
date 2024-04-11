package com.alcity.service.base;

import com.alcity.entity.base.PuzzleLevelStatus;
import com.alcity.repository.base.PuzzleLevelStatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelStatusService implements PuzzleLevelStatusRepository {

    @Autowired
    PuzzleLevelStatusRepository puzzleLevelStatusRepository;

    @Override
    public <S extends PuzzleLevelStatus> S save(S entity) {
        return puzzleLevelStatusRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelStatus> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelStatus> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelStatus> findAll() {
        return puzzleLevelStatusRepository.findAll();
    }

    @Override
    public Iterable<PuzzleLevelStatus> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelStatus entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelStatus> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleLevelStatus findByLabel(String label) {

        return puzzleLevelStatusRepository.findByLabel(label);
    }

    @Override
    public PuzzleLevelStatus findByValue(String value) {
        return puzzleLevelStatusRepository.findByValue(value);
    }
}
