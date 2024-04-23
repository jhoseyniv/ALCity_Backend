package com.alcity.service.alobject;


import com.alcity.entity.alobject.AttributeValue;
import com.alcity.repository.alobject.AttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AttributeValueService implements AttributeValueRepository {

    @Autowired
    AttributeValueRepository ALCityAttributeValueRepository;
    @Override
    public <S extends AttributeValue> S save(S entity) {
        return ALCityAttributeValueRepository.save(entity);
    }

    @Override
    public <S extends AttributeValue> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AttributeValue> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AttributeValue> findAll() {
        return null;
    }

    @Override
    public Iterable<AttributeValue> findAllById(Iterable<Long> longs) {
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
    public void delete(AttributeValue entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AttributeValue> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
