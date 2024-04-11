package com.alcity.service.base;

import com.alcity.entity.base.PuzzleLevelRuleEventType;
import com.alcity.repository.base.PuzzleLevelRuleEventTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.SqlResultSetMapping;
import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleLevelRuleEventTypeService implements PuzzleLevelRuleEventTypeRepository {

    @Autowired
    PuzzleLevelRuleEventTypeRepository puzzleLevelRuleEventTypeRepository;

    @Override
    public <S extends PuzzleLevelRuleEventType> S save(S entity) {
        return puzzleLevelRuleEventTypeRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelRuleEventType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelRuleEventType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelRuleEventType> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelRuleEventType> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelRuleEventType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelRuleEventType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleLevelRuleEventType findByLabel(String label) {

        return puzzleLevelRuleEventTypeRepository.findByLabel(label);
    }

    @Override
    public PuzzleLevelRuleEventType findByValue(String value) {
        return puzzleLevelRuleEventTypeRepository.findByValue(value);
    }
}
