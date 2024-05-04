package com.alcity.service.puzzle;

import com.alcity.entity.puzzle.PLGround;
import com.alcity.repository.puzzle.PLGroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLGroundService implements PLGroundRepository {

    @Autowired
    @Qualifier("PLGroundRepository")
    PLGroundRepository groundRepository;
    @Override
    public <S extends PLGround> S save(S entity) {
        return groundRepository.save(entity);
    }

    @Override
    public <S extends PLGround> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLGround> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public Optional<PLGround> findByPuzzleLevelId(Long id) {
        return groundRepository.findByPuzzleLevelId(id);
    }

     @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLGround> findAll() {
        return null;
    }

    @Override
    public Iterable<PLGround> findAllById(Iterable<Long> longs) {
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
    public void delete(PLGround entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLGround> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PLGround> findByNumColumns(Integer cols) {
        return null;
    }

    @Override
    public Collection<PLGround> findByNumRows(Integer rows) {
        return null;
    }
}
