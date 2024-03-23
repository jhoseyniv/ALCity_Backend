package com.alcity.service.puzzle;


import com.alcity.entity.base.PuzzleLevelRulePostActionType;
import com.alcity.repository.puzzle.PuzzleLevelRulePostActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelRulePostActionTypeService implements PuzzleLevelRulePostActionTypeRepository {

    @Autowired
    PuzzleLevelRulePostActionTypeRepository puzzleLevelRulePostActionTypeRepository;

    @Override
    public <S extends PuzzleLevelRulePostActionType> S save(S entity) {
        return puzzleLevelRulePostActionTypeRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelRulePostActionType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelRulePostActionType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelRulePostActionType> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelRulePostActionType> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelRulePostActionType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelRulePostActionType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleLevelRulePostActionType findByLabel(String label) {
        return null;
    }

    @Override
    public PuzzleLevelRulePostActionType findByValue(String value) {
        return null;
    }
}
