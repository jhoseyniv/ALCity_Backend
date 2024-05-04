package com.alcity.service.base;

import com.alcity.entity.base.PLPrivacy;
import com.alcity.repository.base.PLPrivacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleLevelPrivacyService implements PLPrivacyRepository {

   @Autowired
   PLPrivacyRepository puzzleLevelPrivacyRepository;
    @Override
    public <S extends PLPrivacy> S save(S entity) {
        return puzzleLevelPrivacyRepository.save(entity);
    }

    @Override
    public <S extends PLPrivacy> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLPrivacy> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLPrivacy> findAll() {
        return puzzleLevelPrivacyRepository.findAll();
    }

    @Override
    public Iterable<PLPrivacy> findAllById(Iterable<Long> longs) {
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
    public void delete(PLPrivacy entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLPrivacy> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PLPrivacy findByLabel(String label) {

     return puzzleLevelPrivacyRepository.findByLabel(label);
    }

    @Override
    public PLPrivacy findByValue(String value) {
        return puzzleLevelPrivacyRepository.findByValue(value);
    }
}
