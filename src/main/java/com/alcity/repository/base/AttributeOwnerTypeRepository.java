package com.alcity.repository.base;

import com.alcity.entity.alobject.AttributeOwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AttributeOwnerTypeRepository extends CrudRepository<AttributeOwnerType,Long> {
    Optional<AttributeOwnerType> findById(Long id);
    Collection<AttributeOwnerType> findAll();
    AttributeOwnerType findByLabel(String label);
    AttributeOwnerType findByValue(String value);

}
