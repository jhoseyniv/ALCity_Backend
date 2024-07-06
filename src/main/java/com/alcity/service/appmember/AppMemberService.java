package com.alcity.service.appmember;

import com.alcity.customexception.ALCityResponseObject;
import com.alcity.entity.appmember.AppMember;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.appmember.CustomizedUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class AppMemberService implements AppMemberRepository, CustomizedUserRepository {

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Override
    public <S extends AppMember> S save(S entity) {
        return appMemberRepository.save(entity);
    }

    @Override
    public <S extends AppMember> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMember> findById(Long id) {
        return appMemberRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<AppMember> findAll() {
        return appMemberRepository.findAll();
    }

    @Override
    public Iterable<AppMember> findAllById(Iterable<Long> longs) {
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
    public void delete(AppMember entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMember> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public AppMember findByUsername(String username) {
        return appMemberRepository.findByUsername(username);
    }

    @Override
    public Collection<AppMember> findByMobile(String mobile) {
        return null;
    }

    @Override
    public AppMember findByEmail(String email) {
        return null;
    }

    public ALCityResponseObject login(String username, String password) {
        return null;
    }
}
