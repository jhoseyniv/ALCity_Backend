package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.repository.puzzle.PuzzleGroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PuzzleGroupService  implements PuzzleGroupRepository {

    @Autowired
    PuzzleGroupRepository puzzleGroupRepository;
    @Override
    public <S extends PuzzleGroup> S save(S entity) {
        return puzzleGroupRepository.save(entity);
    }

    @Override
    public <S extends PuzzleGroup> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleGroup> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleGroup> findAll() {
        return null;
    }

    @Override
    public Iterable<PuzzleGroup> findAllById(Iterable<Long> longs) {
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
    public void delete(PuzzleGroup entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PuzzleGroup> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public  Collection<PuzzleGroup> findByTitle(String title) {
        return null;
    }
}
