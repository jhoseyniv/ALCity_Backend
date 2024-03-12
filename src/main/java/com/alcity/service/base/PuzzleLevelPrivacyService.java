package com.alcity.service.base;

import com.alcity.entity.base.PuzzleLevelPrivacy;
import com.alcity.repository.base.PuzzleLevelPrivacyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleLevelPrivacyService implements PuzzleLevelPrivacyRepository {

   @Autowired
    PuzzleLevelPrivacyRepository puzzleLevelPrivacyRepository;
    @Override
    public <S extends PuzzleLevelPrivacy> S save(S entity) {
        return puzzleLevelPrivacyRepository.save(entity);
    }

    @Override
    public <S extends PuzzleLevelPrivacy> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleLevelPrivacy> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleLevelPrivacy> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleLevelPrivacy> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleLevelPrivacy entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleLevelPrivacy> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleLevelPrivacy findByLabel(String label) {
        return null;
    }

    @Override
    public PuzzleLevelPrivacy findByValue(String value) {
        return null;
    }
}
