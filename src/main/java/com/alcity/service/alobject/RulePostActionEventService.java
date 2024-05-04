package com.alcity.service.alobject;

import com.alcity.entity.alobject.RulePostActionEvent;
import com.alcity.repository.alobject.RulePostActionEventRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class RulePostActionEventService implements RulePostActionEventRepository {

    RulePostActionEventRepository rulePostActionEventRepository;
    @Override
    public <S extends RulePostActionEvent> S save(S entity) {
        return rulePostActionEventRepository.save(entity);
    }

    @Override
    public <S extends RulePostActionEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<RulePostActionEvent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<RulePostActionEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<RulePostActionEvent> findAllById(Iterable<Long> longs) {
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
    public void delete(RulePostActionEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends RulePostActionEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
