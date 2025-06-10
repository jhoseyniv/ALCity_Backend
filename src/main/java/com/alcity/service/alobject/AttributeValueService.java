package com.alcity.service.alobject;


import com.alcity.entity.alenum.AttributeOwnerType;
import com.alcity.entity.alobject.Attribute;
import com.alcity.entity.alobject.AttributeValue;
import com.alcity.repository.alobject.AttributeValueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;

@Service
@Transactional
public class AttributeValueService implements AttributeValueRepository {

    @Autowired
    AttributeValueRepository attributeValueRepository;
    @Override
    public <S extends AttributeValue> S save(S entity) {
        return attributeValueRepository.save(entity);
    }


    @Override
    public Optional<AttributeValue> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }



    @Override
    public Collection<AttributeValue> findByAttributeId(Attribute attribute) {
        return attributeValueRepository.findByAttributeId(attribute);
    }

    @Override
    public Optional<AttributeValue> findByAttributeIdAndOwnerId(Attribute attribute, Long ownerId) {
        return attributeValueRepository.findByAttributeIdAndOwnerId(attribute,ownerId);
    }

    @Override
    public Collection<AttributeValue> findByOwnerIdAndOwnerType(Long ownerId, AttributeOwnerType ownerType) {
        return attributeValueRepository.findByOwnerIdAndOwnerType(ownerId,ownerType);
    }

    @Override
    public Collection<AttributeValue> findByOwnerId(Long ownerId) {
        return attributeValueRepository.findByOwnerId(ownerId);
    }

    @Override
    public Optional<AttributeValue> findByAttributeIdAndOwnerIdAndOwnerType(Attribute attribute, Long ownerId, AttributeOwnerType ownerType) {
        return attributeValueRepository.findByAttributeIdAndOwnerIdAndOwnerType(attribute,ownerId,ownerType);
    }



    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(AttributeValue entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AttributeValue> entities) {
        attributeValueRepository.deleteAll();
    }

    @Override
    public void deleteAll() {

    }


    @Override
    public <S extends AttributeValue> List<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public List<AttributeValue> findAll() {
        return null;
    }

    @Override
    public List<AttributeValue> findAllById(Iterable<Long> longs) {
        return null;
    }
}
