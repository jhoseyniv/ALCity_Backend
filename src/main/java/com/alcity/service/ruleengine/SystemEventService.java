package com.alcity.service.ruleengine;

import com.alcity.entity.ruleengine.SystemEvent;
import com.alcity.repository.ruleengine.SystemEventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class SystemEventService implements SystemEventRepository {

    @Autowired
    SystemEventRepository systemEventRepository;
    @Override
    public <S extends SystemEvent> S save(S entity) {
        return systemEventRepository.save(entity);
    }

    @Override
    public <S extends SystemEvent> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<SystemEvent> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<SystemEvent> findAll() {
        return null;
    }

    @Override
    public Iterable<SystemEvent> findAllById(Iterable<Long> longs) {
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
    public void delete(SystemEvent entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends SystemEvent> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public SystemEvent findByLabel(String label) {
        return null;
    }

    @Override
    public SystemEvent findByValue(String value) {
        return null;
    }
}
