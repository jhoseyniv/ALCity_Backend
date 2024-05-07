package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.repository.puzzle.PGRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PGService implements PGRepository {

    @Qualifier("PGRepository")
    @Autowired
    PGRepository pgRepository;
    @Override
    public <S extends PuzzleGroup> S save(S entity) {
        return pgRepository.save(entity);
    }

    @Override
    public <S extends PuzzleGroup> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PuzzleGroup> findById(Long id) {
        return pgRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PuzzleGroup> findAll() {
        return pgRepository.findAll();
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
    public  Optional<PuzzleGroup> findByTitle(String title) {
        return pgRepository.findByTitle(title);
    }
}
