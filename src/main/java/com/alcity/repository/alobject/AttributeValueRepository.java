package com.alcity.repository.alobject;

import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.Optional;

public interface AttributeValueRepository extends CrudRepository<AttributeValue,Long> {
    Optional<AttributeValue> findById(Long id);
    Collection<AttributeValue> findByAttributeId(Attribute attribute);
    Optional<AttributeValue> findByAttributeIdAndOwnerId(Attribute attribute,Long ownerId);
    Collection<AttributeValue> findByOwnerIdAndOwnerType(Long ownerId,AttributeOwnerType ownerType);
    Optional<AttributeValue> findByAttributeIdAndOwnerIdAndOwnerType(Attribute attribute, Long ownerId, AttributeOwnerType ownerType);
}
