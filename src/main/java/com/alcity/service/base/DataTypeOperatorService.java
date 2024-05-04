package com.alcity.service.base;

import com.alcity.entity.base.DataTypeOperator;
import com.alcity.repository.base.DataTypeOperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class DataTypeOperatorService implements DataTypeOperatorRepository {

    @Autowired
    DataTypeOperatorRepository dataTypeOperatorRepository;

    @Override
    public <S extends DataTypeOperator> S save(S entity) {
        return dataTypeOperatorRepository.save(entity);
    }

    @Override
    public <S extends DataTypeOperator> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DataTypeOperator> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<DataTypeOperator> findAll() {
        return null;
    }

    @Override
    public Iterable<DataTypeOperator> findAllById(Iterable<Long> longs) {
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
    public void delete(DataTypeOperator entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DataTypeOperator> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public DataTypeOperator findByLabel(String label) {
        return null;
    }

    @Override
    public DataTypeOperator findByValue(String value) {
        return null;
    }

    @Override
    public DataTypeOperator findByDataTypeId(Long id) {
        return null;
    }
}
