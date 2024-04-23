package com.alcity.repository.alobject;

import com.alcity.entity.alobject.AttributeValue;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AttributeValueRepository extends CrudRepository<AttributeValue,Long> {
    Optional<AttributeValue> findById(Long id);
    Collection<AttributeValue> findAll();

}
