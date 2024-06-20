package com.alcity.service.users;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.entity.users.ApplicationMember;
import com.alcity.repository.users.ApplicationMemberRepository;
import com.alcity.repository.users.CustomizedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class ApplicationMemberService implements ApplicationMemberRepository, CustomizedUserRepository {

    @Autowired
    private ApplicationMemberRepository applicationMemberRepository;

    @Override
    public <S extends ApplicationMember> S save(S entity) {
        return applicationMemberRepository.save(entity);
    }

    @Override
    public <S extends ApplicationMember> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ApplicationMember> findById(Long id) {
        return applicationMemberRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<ApplicationMember> findAll() {
        return applicationMemberRepository.findAll();
    }

    @Override
    public Iterable<ApplicationMember> findAllById(Iterable<Long> longs) {
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
    public void delete(ApplicationMember entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ApplicationMember> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public ApplicationMember findByUsername(String username) {
        return applicationMemberRepository.findByUsername(username);
    }

    @Override
    public Collection<ApplicationMember> findByMobile(String mobile) {
        return null;
    }

    @Override
    public ApplicationMember findByEmail(String email) {
        return null;
    }

    public ALCityResponseObject login(String username, String password) {
        return null;
    }
}
