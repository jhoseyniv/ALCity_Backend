package com.alcity.repository.base;

import com.alcity.entity.base.DataType;
import com.alcity.entity.base.PuzzleCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface DataTypeRepository extends CrudRepository<DataType,Long> {
    Optional<DataType> findById(Long id);
    Collection<DataType> findAll();
    DataType findByLabel(String label);
    DataType findByValue(String value);

}
