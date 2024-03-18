package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PuzzleLevelRuleEvent;
import com.alcity.repository.puzzle.PuzzleLevelRuleEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelRuleEventService implements PuzzleLevelRuleEventRepository {

   @Autowired
    PuzzleLevelRuleEventRepository puzzleLevelRuleEventRepository;
    @Override
    public <S extends PuzzleLevelRuleEvent> S save(S entity) {
        return puzzleLevelRuleEventRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelRuleEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelRuleEvent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelRuleEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelRuleEvent> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelRuleEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelRuleEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleLevelRuleEvent> findByName(String name) {
        return null;
    }
}
