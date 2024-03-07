package com.alcity.service.base;


import com.alcity.entity.base.MemberType;
import com.alcity.repository.base.MemberTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional

public class MemberTypeService implements MemberTypeRepository {

    @Autowired
    private MemberTypeRepository memberTypeRepository;

    @Override
    public <S extends MemberType> S save(S entity) {
        return memberTypeRepository.save(entity);
    }

    @Override
    public <S extends MemberType> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<MemberType> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<MemberType> findAll() {
        return null;
    }

    @Override
    public Iterable<MemberType> findAllById(Iterable<Long> longs) {
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
    public void delete(MemberType entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends MemberType> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public MemberType findByLabel(String label) {
        return null;
    }

    @Override
    public MemberType findByValue(String value) {
        return null;
    }
}
