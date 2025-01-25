package com.alcity.service.appmember;


import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.Authority;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AuthorityService implements AuthorityRepository {
    @Autowired
    private AuthorityRepository authorityRepository;

    @Override
    public <S extends Authority> S save(S entity) {
        return authorityRepository.save(entity);
    }

    @Override
    public <S extends Authority> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Authority> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<Authority> findAll() {
        return null;
    }

    @Override
    public Iterable<Authority> findAllById(Iterable<Long> longs) {
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
    public void delete(Authority entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Authority> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public AppMember findByName(String name) {
        return null;
    }
}
