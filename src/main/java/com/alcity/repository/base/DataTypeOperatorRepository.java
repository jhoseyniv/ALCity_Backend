package com.alcity.repository.base;

import com.alcity.entity.base.DataType;
import com.alcity.entity.base.DataTypeOperator;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface DataTypeOperatorRepository extends CrudRepository<DataTypeOperator,Long> {
    Optional<DataTypeOperator> findById(Long id);
    Collection<DataTypeOperator> findAll();
    DataTypeOperator findByLabel(String label);
    DataTypeOperator findByValue(String value);
    DataTypeOperator findByDataTypeId(Long id);

}
