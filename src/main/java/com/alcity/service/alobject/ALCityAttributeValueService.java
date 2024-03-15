package com.alcity.service.alobject;


import com.alcity.entity.alobject.ALCityAttributeValue;
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
    public <S extends ALCityAttributeValue> S save(S entity) {
        return ALCityAttributeValueRepository.save(entity);
    }

    @Override
    public <S extends ALCityAttributeValue> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityAttributeValue> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityAttributeValue> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityAttributeValue> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityAttributeValue entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityAttributeValue> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
