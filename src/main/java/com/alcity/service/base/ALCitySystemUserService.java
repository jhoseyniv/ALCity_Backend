package com.alcity.service.base;


import com.alcity.entity.base.ALCitySystemUser;
import com.alcity.repository.base.ALCitySystemUserRepository;
import com.alcity.repository.base.DataTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ALCitySystemUserService implements ALCitySystemUserRepository {

    @Autowired
    private ALCitySystemUserRepository ALCitySystemUserRepository;


    @Override
    public <S extends ALCitySystemUser> S save(S entity) {
        return ALCitySystemUserRepository.save(entity);
    }

    @Override
    public <S extends ALCitySystemUser> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCitySystemUser> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ALCitySystemUser> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCitySystemUser> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCitySystemUser entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCitySystemUser> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
