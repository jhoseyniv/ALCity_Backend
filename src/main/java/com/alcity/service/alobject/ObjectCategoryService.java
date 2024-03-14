package com.alcity.service.alobject;

import com.alcity.entity.alobject.ObjectCategory;
import com.alcity.repository.alobject.ObjectCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ObjectCategoryService implements ObjectCategoryRepository {

    @Autowired
    ObjectCategoryRepository objectCategoryRepository;

    @Override
    public <S extends ObjectCategory> S save(S entity) {
        return objectCategoryRepository.save(entity);
    }

    @Override
    public <S extends ObjectCategory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ObjectCategory> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ObjectCategory> findAll() {
        return null;
    }

    @Override
    public Iterable<ObjectCategory> findAllById(Iterable<Long> longs) {
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
    public void delete(ObjectCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ObjectCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ObjectCategory findByLabel(String label) {
        return null;
    }

    @Override
    public ObjectCategory findByValue(String value) {
        return null;
    }
}
