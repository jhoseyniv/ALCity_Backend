package com.alcity.service.base;

import com.alcity.entity.base.DataTypeOperator;
import com.alcity.entity.base.ExprFunction;
import com.alcity.repository.base.ExprFunctionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ExprFunctionService implements ExprFunctionRepository {

    @Autowired
    ExprFunctionRepository exprFunctionRepository;
    @Override
    public <S extends ExprFunction> S save(S entity) {
        return exprFunctionRepository.save(entity);
    }

    @Override
    public <S extends ExprFunction> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ExprFunction> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ExprFunction> findAll() {
        return null;
    }

    @Override
    public Iterable<ExprFunction> findAllById(Iterable<Long> longs) {
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
    public void delete(ExprFunction entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ExprFunction> entities) {

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
}
