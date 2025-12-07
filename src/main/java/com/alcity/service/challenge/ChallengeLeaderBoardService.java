package com.alcity.service.challenge;

import com.alcity.entity.challenge.ChallengeLeaderBoard;
import com.alcity.repository.challenge.ChallengeLeaderBoardRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ChallengeLeaderBoardService implements ChallengeLeaderBoardRepository {

    @Override
    public <S extends ChallengeLeaderBoard> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ChallengeLeaderBoard> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ChallengeLeaderBoard> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ChallengeLeaderBoard> findAll() {
        return null;
    }

    @Override
    public Iterable<ChallengeLeaderBoard> findAllById(Iterable<Long> longs) {
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
    public void delete(ChallengeLeaderBoard entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChallengeLeaderBoard> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
