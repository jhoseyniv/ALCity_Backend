package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleLevelRulePostAction;
import com.alcity.repository.puzzle.PuzzleLevelRulePostActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelRulePostActionService implements PuzzleLevelRulePostActionRepository {

    @Autowired
    PuzzleLevelRulePostActionRepository puzzleLevelRulePostActionRepository;

    @Override
    public <S extends PuzzleLevelRulePostAction> S save(S entity) {
        return puzzleLevelRulePostActionRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelRulePostAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelRulePostAction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelRulePostAction> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelRulePostAction> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelRulePostAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelRulePostAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PuzzleLevelRulePostAction> findByOrdering(Integer ordering) {
        return null;
    }

    @Override
    public Collection<PuzzleLevelRulePostAction> findByActionExpression(String experssion) {
        return null;
    }
}
