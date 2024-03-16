package com.alcity.repository.base;

import com.alcity.entity.base.DataTypeOperator;
import com.alcity.entity.base.ExprFunction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ExprFunctionRepository extends CrudRepository<ExprFunction,Long> {
    Optional<ExprFunction> findById(Long id);
    Collection<ExprFunction> findAll();
    DataTypeOperator findByLabel(String label);
    DataTypeOperator findByValue(String value);

}
