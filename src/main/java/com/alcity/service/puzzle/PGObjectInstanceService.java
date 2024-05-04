package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PGObjectInstance;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.repository.puzzle.PGObjectInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PGObjectInstanceService implements PGObjectInstanceRepository {

    @Autowired
    @Qualifier("PGObjectInstanceRepository")
    PGObjectInstanceRepository  pgOjectInstanceRepository;

    @Override
    public <S extends PGObjectInstance> S save(S entity) {
        return pgOjectInstanceRepository.save(entity);
    }

    @Override
    public <S extends PGObjectInstance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PGObjectInstance> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PGObjectInstance> findAll() {
        return null;
    }

    @Override
    public Iterable<PGObjectInstance> findAllById(Iterable<Long> longs) {
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
    public void delete(PGObjectInstance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PGObjectInstance> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PGObjectInstance> findByRow(Integer row) {
        return null;
    }

    @Override
    public Collection<PGObjectInstance> findByCol(Integer col) {
        return null;
    }

    @Override
    public Collection<PGObjectInstance> findByzOrder(Integer zOrder) {
        return null;
    }

    @Override
    public Collection<PGObjectInstance> findByPuzzleLevel(PLObjective pl) {
        return pgOjectInstanceRepository.findByPuzzleLevel(pl);
    }
}
