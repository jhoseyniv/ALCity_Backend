package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.ALCityInstanceInPL;
import com.alcity.entity.puzzle.ALCityObjectInPG;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.puzzle.ALCityInstanceInPLRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ALCityInstanceInPLService implements ALCityInstanceInPLRepository {

    @Autowired
    @Qualifier("ALCityInstanceInPLRepository")
    ALCityInstanceInPLRepository alCityInstanceInPLRepository;

    @Override
    public <S extends ALCityInstanceInPL> S save(S entity) {
        return alCityInstanceInPLRepository.save(entity);
    }

    @Override
    public <S extends ALCityInstanceInPL> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityInstanceInPL> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityInstanceInPL> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityInstanceInPL> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityInstanceInPL entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityInstanceInPL> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityInstanceInPL> findByRow(Integer row) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByCol(Integer col) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByzOrder(Integer zOrder) {
        return null;
    }

    @Override
    public Collection<ALCityInstanceInPL> findByAlCityObjectInPGAndPuzzleLevel(ALCityObjectInPG pgObject, PuzzleLevel pl) {
        return alCityInstanceInPLRepository.findByAlCityObjectInPGAndPuzzleLevel(pgObject,pl);
    }



 }
