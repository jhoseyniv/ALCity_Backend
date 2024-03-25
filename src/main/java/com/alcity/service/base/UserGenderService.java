package com.alcity.service.base;

import com.alcity.entity.base.UserGender;
import com.alcity.repository.base.UserGenderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class UserGenderService implements UserGenderRepository {

    @Autowired
    private UserGenderRepository userGenderRepository;

    @Override
    public <S extends UserGender> S save(S entity) {

        return userGenderRepository.save(entity);
    }

    @Override
    public <S extends UserGender> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<UserGender> findById(Long id) {
        return userGenderRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public List<UserGender> findAll() {
        return userGenderRepository.findAll();
    }

    @Override
    public Iterable<UserGender> findAllById(Iterable<Long> longs) {
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
    public void delete(UserGender entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends UserGender> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public UserGender findByLabel(String bLabel) {
        return null;
    }
    @Override
    public UserGender findByValue(String bValue) {
        return null;
    }
}
