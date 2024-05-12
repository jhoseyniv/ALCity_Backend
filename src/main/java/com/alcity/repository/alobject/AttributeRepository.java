package com.alcity.repository.alobject;

import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alenum.AttributeOwnerType;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AttributeRepository extends CrudRepository<Attribute,Long> {
    Optional<Attribute> findById(Long id);
    Collection<Attribute> findAll();
    Collection<Attribute> findByName(String name);
    Collection<Attribute> findByOwnerId(Long ownerId);
    Collection<Attribute>  findByOwnerIdAndAttributeOwnerType(Long instanceId, AttributeOwnerType OwnerType);
    Optional<Attribute> findByOwnerIdAndName(Long ownerId,String name);

}
