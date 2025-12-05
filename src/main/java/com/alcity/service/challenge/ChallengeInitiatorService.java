package com.alcity.service.challenge;

import com.alcity.entity.challenge.ChallengeInitiator;
import com.alcity.repository.challenge.ChallengeInitiatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ChallengeInitiatorService  implements ChallengeInitiatorRepository {
    @Override
    public <S extends ChallengeInitiator> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ChallengeInitiator> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ChallengeInitiator> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ChallengeInitiator> findAll() {
        return null;
    }

    @Override
    public Iterable<ChallengeInitiator> findAllById(Iterable<Long> longs) {
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
    public void delete(ChallengeInitiator entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChallengeInitiator> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
