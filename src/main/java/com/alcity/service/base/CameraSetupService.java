package com.alcity.service.base;

import com.alcity.entity.base.CameraSetup;
import com.alcity.repository.base.CameraSetupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class CameraSetupService implements CameraSetupRepository {

    @Autowired
    private CameraSetupRepository cameraSetupRepository ;


    @Override
    public <S extends CameraSetup> S save(S entity) {
        return null;
    }

    @Override
    public <S extends CameraSetup> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<CameraSetup> findById(Long aLong) {
        return cameraSetupRepository.findById(aLong);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<CameraSetup> findAll() {
        return cameraSetupRepository.findAll();
    }

    @Override
    public Iterable<CameraSetup> findAllById(Iterable<Long> longs) {
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
    public void delete(CameraSetup entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends CameraSetup> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
