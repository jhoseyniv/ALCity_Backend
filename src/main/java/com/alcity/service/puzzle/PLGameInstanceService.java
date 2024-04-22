package com.alcity.service.puzzle;


import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.repository.puzzle.PLGameInstanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PLGameInstanceService implements PLGameInstanceRepository {

    @Autowired
    @Qualifier("PLGameInstanceRepository")

    PLGameInstanceRepository plGameInstanceRepository;


    @Override
    public <S extends PLGameInstance> S save(S entity) {
        return plGameInstanceRepository.save(entity);
    }

    @Override
    public <S extends PLGameInstance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLGameInstance> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLGameInstance> findAll() {
        return null;
    }

    @Override
    public Iterable<PLGameInstance> findAllById(Iterable<Long> longs) {
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
    public void delete(PLGameInstance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLGameInstance> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
