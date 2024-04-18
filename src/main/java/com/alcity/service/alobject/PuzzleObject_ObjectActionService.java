package com.alcity.service.alobject;

import com.alcity.entity.alobject.ActionRenderer;
import com.alcity.entity.alobject.PuzzleObject_ObjectAction;
import com.alcity.repository.alobject.PuzzleObject_ObjectActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleObject_ObjectActionService implements PuzzleObject_ObjectActionRepository {

    @Autowired
    PuzzleObject_ObjectActionRepository puzzleObject_ObjectActionRepository;

    @Override
    public <S extends PuzzleObject_ObjectAction> S save(S entity) {
        return puzzleObject_ObjectActionRepository.save(entity);
    }

    @Override
    public <S extends PuzzleObject_ObjectAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleObject_ObjectAction> findById(Long id) {
        return puzzleObject_ObjectActionRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleObject_ObjectAction> findAll() {
        return puzzleObject_ObjectActionRepository.findAll();
    }

    @Override
    public Iterable<PuzzleObject_ObjectAction> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleObject_ObjectAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleObject_ObjectAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleObject_ObjectAction> findByActionRendererId(Long id) {
        return null;
    }

    @Override
    public Collection<PuzzleObject_ObjectAction> findByPoActionOwnerType(Long id) {
        return null;
    }

    @Override
    public Collection<PuzzleObject_ObjectAction> findByOwnerObjectid(Long id) {
        return puzzleObject_ObjectActionRepository.findByOwnerObjectid(id);
    }
}
