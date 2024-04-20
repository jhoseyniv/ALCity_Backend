package com.alcity.service.alobject;


import com.alcity.entity.alobject.ALAttributeValue;
import com.alcity.repository.alobject.ALCityAttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ALCityAttributeValueService implements ALCityAttributeValueRepository {

    @Autowired
    ALCityAttributeValueRepository ALCityAttributeValueRepository;
    @Override
    public <S extends ALAttributeValue> S save(S entity) {
        return ALCityAttributeValueRepository.save(entity);
    }

    @Override
    public <S extends ALAttributeValue> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALAttributeValue> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALAttributeValue> findAll() {
        return null;
    }

    @Override
    public Iterable<ALAttributeValue> findAllById(Iterable<Long> longs) {
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
    public void delete(ALAttributeValue entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALAttributeValue> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
