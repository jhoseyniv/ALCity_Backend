package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ALAttributeValue;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityAttributeValueRepository extends CrudRepository<ALAttributeValue,Long> {
    Optional<ALAttributeValue> findById(Long id);
    Collection<ALAttributeValue> findAll();

}
