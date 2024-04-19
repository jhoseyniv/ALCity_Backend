package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PLRulePostAction;
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
    public <S extends PLRulePostAction> S save(S entity) {
        return puzzleLevelRulePostActionRepository.save(entity);
    }

    @Override
    public <S extends PLRulePostAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRulePostAction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLRulePostAction> findAll() {
        return null;
    }

    @Override
    public Iterable<PLRulePostAction> findAllById(Iterable<Long> longs) {
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
    public void delete(PLRulePostAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRulePostAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLRulePostAction> findByOrdering(Integer ordering) {
        return null;
    }

    @Override
    public Collection<PLRulePostAction> findByActionExpression(String experssion) {
        return null;
    }
}
