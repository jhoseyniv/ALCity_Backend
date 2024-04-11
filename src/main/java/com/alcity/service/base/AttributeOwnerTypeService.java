package com.alcity.service.base;

import com.alcity.entity.alobject.AttributeOwnerType;
import com.alcity.repository.base.AttributeOwnerTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AttributeOwnerTypeService implements AttributeOwnerTypeRepository {

   @Autowired
   AttributeOwnerTypeRepository attributeOwnerTypeRepository;

   @Override
    public <S extends AttributeOwnerType> S save(S entity) {
        return attributeOwnerTypeRepository.save(entity);
    }

    @Override
    public <S extends AttributeOwnerType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AttributeOwnerType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AttributeOwnerType> findAll() {
        return null;
    }

    @Override
    public Iterable<AttributeOwnerType> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {

    }

    @Override
    public void delete(AttributeOwnerType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AttributeOwnerType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public AttributeOwnerType findByLabel(String label) {

       return attributeOwnerTypeRepository.findByLabel(label);
    }

    @Override
    public AttributeOwnerType findByValue(String value) {
        return attributeOwnerTypeRepository.findByValue(value);
    }
}
