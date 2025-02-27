package com.alcity.service.alobject;

import com.alcity.entity.alobject.ObjectAction;
import com.alcity.entity.alobject.PLRulePostActionType;
import com.alcity.repository.alobject.PLRulePostActionTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class PLRulePostActionTypeService implements PLRulePostActionTypeRepository {

    @Qualifier("PLRulePostActionTypeRepository")
    @Autowired
    PLRulePostActionTypeRepository repository;
    @Override
    public <S extends PLRulePostActionType> S save(S entity) {
        return repository.save(entity);
    }

    @Override
    public <S extends PLRulePostActionType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLRulePostActionType> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<PLRulePostActionType> findAll() {
        return null;
    }

    @Override
    public Iterable<PLRulePostActionType> findAllById(Iterable<Long> longs) {
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
    public void delete(PLRulePostActionType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLRulePostActionType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Optional<PLRulePostActionType> findByValue(String value) {
        return repository.findByValue(value);
    }
}
