package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ALCityAttribute;
import com.alcity.entity.alobject.ObjectAction;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityAttributeRepository extends CrudRepository<ALCityAttribute,Long> {
    Optional<ALCityAttribute> findById(Long id);
    Collection<ALCityAttribute> findAll();
    Collection<ALCityAttribute> findByName(String name);
    Collection<ALCityAttribute> findByOwnerId(Long ownerId);

}
