package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.repository.puzzle.ALCityObjectInPGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class ALCityObjectInPGService implements ALCityObjectInPGRepository {

    @Autowired
    @Qualifier("ALCityObjectInPGRepository")
    ALCityObjectInPGRepository alCityObjectInPGRepository;

    @Override
    public <S extends ALCityObjectInPG> S save(S entity) {
        return alCityObjectInPGRepository.save(entity);
    }

    @Override
    public <S extends ALCityObjectInPG> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityObjectInPG> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityObjectInPG> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityObjectInPG> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityObjectInPG entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityObjectInPG> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityObjectInPG> findByTitle(String title) {
        return null;
    }

    @Override
    public Collection<ALCityObjectInPG> findByCode(String code) {
        return alCityObjectInPGRepository.findByCode(code);
    }

    @Override
    public Optional<ALCityObjectInPG> findByCodeAndTitle(String code, String title) {
        return alCityObjectInPGRepository.findByCodeAndTitle(code,title);
    }
}