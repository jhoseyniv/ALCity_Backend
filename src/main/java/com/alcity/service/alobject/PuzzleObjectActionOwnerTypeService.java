package com.alcity.service.alobject;

import com.alcity.entity.alobject.PuzzleObjectActionOwnerType;
import com.alcity.repository.alobject.PuzzleObjectActionOwnerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;


@Service
@Transactional
public class PuzzleObjectActionOwnerTypeService implements PuzzleObjectActionOwnerTypeRepository {

    @Autowired
    PuzzleObjectActionOwnerTypeRepository puzzleObjectActionOwnerTypeRepository;

    @Override
    public <S extends PuzzleObjectActionOwnerType> S save(S entity) {
        return puzzleObjectActionOwnerTypeRepository.save(entity);
    }

    @Override
    public <S extends PuzzleObjectActionOwnerType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleObjectActionOwnerType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleObjectActionOwnerType> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleObjectActionOwnerType> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleObjectActionOwnerType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleObjectActionOwnerType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public PuzzleObjectActionOwnerType findByLabel(String label) {
        return puzzleObjectActionOwnerTypeRepository.findByLabel(label);
    }

    @Override
    public PuzzleObjectActionOwnerType findByValue(String value) {
        return puzzleObjectActionOwnerTypeRepository.findByValue(value);
    }
}
