package com.alcity.service.challenge;


import com.alcity.entity.challenge.Challenge;
import com.alcity.repository.challenge.ChallengeRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional

public class ChallengeService  implements ChallengeRepository {
    @Override
    public <S extends Challenge> S save(S entity) {
        return null;
    }

    @Override
    public <S extends Challenge> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<Challenge> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<Challenge> findAll() {
        return null;
    }

    @Override
    public Iterable<Challenge> findAllById(Iterable<Long> longs) {
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
    public void delete(Challenge entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends Challenge> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
