package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.repository.puzzle.PLRuleEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLRuleEventService implements PLRuleEventRepository {

   @Autowired
   PLRuleEventRepository puzzleLevelRuleEventRepository;
    @Override
    public <S extends PLRuleEvent> S save(S entity) {
        return puzzleLevelRuleEventRepository.save(entity);
    }

    @Override
    public <S extends PLRuleEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRuleEvent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLRuleEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<PLRuleEvent> findAllById(Iterable<Long> longs) {
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
    public void delete(PLRuleEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRuleEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLRuleEvent> findByName(String name) {
        return null;
    }
}
