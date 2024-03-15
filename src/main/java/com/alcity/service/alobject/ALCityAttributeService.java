package com.alcity.service.alobject;

import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.repository.alobject.ALCityAttributeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ALCityAttributeService implements ALCityAttributeRepository {

    @Autowired
    ALCityAttributeRepository ALCityAttributeRepository;

    @Override
    public <S extends ALCityAttribute> S save(S entity) {
        return ALCityAttributeRepository.save(entity);
    }

    @Override
    public <S extends ALCityAttribute> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityAttribute> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityAttribute> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityAttribute> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityAttribute entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityAttribute> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityAttribute> findByName(String name) {
        return null;
    }

    @Override
    public Collection<ALCityAttribute> findByOwnerId(Long ownerId) {
        return null;
    }
}
