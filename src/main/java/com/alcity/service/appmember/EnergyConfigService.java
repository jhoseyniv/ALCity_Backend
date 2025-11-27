package com.alcity.service.appmember;

import com.alcity.entity.appmember.EnergyConfig;
import com.alcity.repository.appmember.EnergyConfigRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class EnergyConfigService implements EnergyConfigRepository{
    @Autowired
    EnergyConfigRepository energyConfigRepository;

    @Override
    public <S extends EnergyConfig> S save(S entity) {
        return null;
    }

    @Override
    public <S extends EnergyConfig> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<EnergyConfig> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<EnergyConfig> findAll() {
        return null;
    }

    @Override
    public Iterable<EnergyConfig> findAllById(Iterable<Long> longs) {
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
    public void delete(EnergyConfig entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends EnergyConfig> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<EnergyConfig> findById(long id) {
        return Optional.empty();
    }

    @Override
    public Optional<EnergyConfig> findByExpireIsFalse() {
        return energyConfigRepository.findByExpireIsFalse() ;
    }
}
