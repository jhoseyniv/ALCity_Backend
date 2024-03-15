package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ALCityAttributeValue;
import com.alcity.entity.alobject.ObjectAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityAttributeValueRepository extends CrudRepository<ALCityAttributeValue,Long> {
    Optional<ALCityAttributeValue> findById(Long id);
    Collection<ALCityAttributeValue> findAll();

}
