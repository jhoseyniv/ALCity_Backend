package com.alcity.service.ruleengine;

import com.alcity.entity.ruleengine.UserEvent;
import com.alcity.repository.ruleengine.UserEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class UserEventService implements UserEventRepository {

    @Autowired
    UserEventRepository userEventRepository;

    @Override
    public <S extends UserEvent> S save(S entity) {
        return userEventRepository.save(entity);
    }

    @Override
    public <S extends UserEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserEvent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<UserEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<UserEvent> findAllById(Iterable<Long> longs) {
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
    public void delete(UserEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserEvent findByLabel(String label) {
        return null;
    }

    @Override
    public UserEvent findByValue(String value) {
        return null;
    }
}
