package com.alcity.service.alobject;


import com.alcity.entity.alobject.ObjectActionEvent;
import com.alcity.repository.alobject.ActionEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ActionEventService implements ActionEventRepository {
    @Autowired
    ActionEventRepository actionEventRepository;

    @Override
    public <S extends ObjectActionEvent> S save(S entity) {
        return actionEventRepository.save(entity);
    }

    @Override
    public <S extends ObjectActionEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ObjectActionEvent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ObjectActionEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<ObjectActionEvent> findAllById(Iterable<Long> longs) {
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
    public void delete(ObjectActionEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ObjectActionEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
