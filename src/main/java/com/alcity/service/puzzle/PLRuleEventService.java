package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PLRuleEvent;
import com.alcity.repository.puzzle.PLRuleEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLRuleEventService implements PLRuleEventRepository {

   @Autowired
   @Qualifier("PLRuleEventRepository")
   PLRuleEventRepository plRuleEventRepository ;
    @Override
    public <S extends PLRuleEvent> S save(S entity) {
        return plRuleEventRepository.save(entity);
    }

    @Override
    public <S extends PLRuleEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRuleEvent> findById(Long id) {
        return
                plRuleEventRepository.findById(id);
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
    public Optional<PLRuleEvent> findByName(String name) {
        return plRuleEventRepository.findByName(name);
    }
}
