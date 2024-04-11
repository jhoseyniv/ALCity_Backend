package com.alcity.service.alobject;

import com.alcity.entity.alobject.ObjectAction;
import com.alcity.repository.alobject.ObjectActionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ObjectActionService implements ObjectActionRepository {
    @Autowired
    ObjectActionRepository objectActionRepository;

    @Override
    public <S extends ObjectAction> S save(S entity) {
        return objectActionRepository.save(entity);
    }

    @Override
    public <S extends ObjectAction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ObjectAction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ObjectAction> findAll() {
        return null;
    }

    @Override
    public Iterable<ObjectAction> findAllById(Iterable<Long> longs) {
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
    public void delete(ObjectAction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ObjectAction> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ObjectAction findByLabel(String label) {

        return objectActionRepository.findByLabel(label);
    }

    @Override
    public ObjectAction findByValue(String value) {
        return objectActionRepository.findByValue(value);
    }
}
