package com.alcity.repository.alobject;

import com.alcity.entity.alobject.ALAttribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface ALCityAttributeRepository extends CrudRepository<ALAttribute,Long> {
    Optional<ALAttribute> findById(Long id);
    Collection<ALAttribute> findAll();
    Collection<ALAttribute> findByName(String name);
    Collection<ALAttribute> findByOwnerId(Long ownerId);
    Collection<ALAttribute>  findByOwnerIdAndAttributeOwnerType(Long instanceId, AttributeOwnerType OwnerType);

}
