package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.ALCityObject;
import com.alcity.repository.puzzle.PuzzleObjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleObjectService implements PuzzleObjectRepository {

    @Autowired
    PuzzleObjectRepository puzzleObjectRepository;

    @Override
    public <S extends ALCityObject> S save(S entity) {

        return puzzleObjectRepository.save(entity);
    }

    @Override
    public <S extends ALCityObject> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ALCityObject> findById(Long id) {
        return puzzleObjectRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ALCityObject> findAll() {
        return null;
    }

    @Override
    public Iterable<ALCityObject> findAllById(Iterable<Long> longs) {
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
    public void delete(ALCityObject entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ALCityObject> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<ALCityObject> findByTitle(String title) {
        return null;
    }


}
