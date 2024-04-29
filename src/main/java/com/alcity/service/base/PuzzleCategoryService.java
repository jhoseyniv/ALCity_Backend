package com.alcity.service.base;


import com.alcity.entity.base.PuzzleCategory;
import com.alcity.repository.base.PuzzleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleCategoryService implements PuzzleCategoryRepository {

    @Autowired
    private PuzzleCategoryRepository puzzleCategoryRepository;

    @Override
    public <S extends PuzzleCategory> S save(S entity) {
        return puzzleCategoryRepository.save(entity);
    }

    @Override
    public <S extends PuzzleCategory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleCategory> findById(Long id) {
        return puzzleCategoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleCategory> findAll() {
        return puzzleCategoryRepository.findAll();
    }

    @Override
    public Iterable<PuzzleCategory> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleCategory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleCategory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleCategory findByLabel(String label) {
        return puzzleCategoryRepository.findByLabel(label);
    }

    @Override
    public PuzzleCategory findByValue(String value) {
        return puzzleCategoryRepository.findByValue(value);
    }

    @Override
    public Collection<PuzzleCategory> findByValueContains(String criteria) {
        return puzzleCategoryRepository.findByValueContains(criteria);
    }
}
