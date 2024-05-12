package com.alcity.service.alobject;

import com.alcity.entity.alobject.PuzzleObjectAction;
import com.alcity.repository.alobject.PuzzleObjectActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleObjectActionService implements PuzzleObjectActionRepository {

    @Autowired
    PuzzleObjectActionRepository puzzleObject_ObjectActionRepository;

    @Override
    public <S extends PuzzleObjectAction> S save(S entity) {
        return puzzleObject_ObjectActionRepository.save(entity);
    }

    @Override
    public <S extends PuzzleObjectAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleObjectAction> findById(Long id) {
        return puzzleObject_ObjectActionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleObjectAction> findAll() {
        return puzzleObject_ObjectActionRepository.findAll();
    }

    @Override
    public Iterable<PuzzleObjectAction> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleObjectAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleObjectAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleObjectAction> findByActionRendererId(Long id) {
        return null;
    }

    @Override
    public Collection<PuzzleObjectAction> findByPoActionOwnerType(Long id) {
        return null;
    }

    @Override
    public Collection<PuzzleObjectAction> findByOwnerObjectid(Long id) {
        return puzzleObject_ObjectActionRepository.findByOwnerObjectid(id);
    }
}
