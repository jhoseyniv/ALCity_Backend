package com.alcity.service.base;

import com.alcity.customexception.UniqueConstraintException;
import com.alcity.entity.base.DataType;
import com.alcity.repository.base.DataTypeRepository;
import com.alcity.repository.base.PuzzleCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class DataTypeService implements DataTypeRepository {

    @Autowired
    private DataTypeRepository dataTypeRepository;

    @Override
    public <S extends DataType> S save(S entity) {
        return  dataTypeRepository.save(entity);
    }

    @Override
    public <S extends DataType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<DataType> findById(Long id) {
        return dataTypeRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<DataType> findAll() {
        return dataTypeRepository.findAll();
    }

    @Override
    public Iterable<DataType> findAllById(Iterable<Long> longs) {
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
    public void delete(DataType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends DataType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public DataType findByLabel(String label) {

        return dataTypeRepository.findByLabel(label);
    }

    @Override
    public DataType findByValue(String value) {
        return dataTypeRepository.findByValue(value);
    }
}
