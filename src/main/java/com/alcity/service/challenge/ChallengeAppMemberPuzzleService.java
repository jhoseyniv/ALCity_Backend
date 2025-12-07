package com.alcity.service.challenge;

import com.alcity.entity.challenge.ChallengeAppMemberPuzzle;
import com.alcity.entity.challenge.ChallengeInitiator;
import com.alcity.repository.challenge.ChallengeAppMemberPuzzleRepository;
import com.alcity.repository.challenge.ChallengeInitiatorRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ChallengeAppMemberPuzzleService  implements ChallengeAppMemberPuzzleRepository {
    @Override
    public <S extends ChallengeAppMemberPuzzle> S save(S entity) {
        return null;
    }

    @Override
    public <S extends ChallengeAppMemberPuzzle> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<ChallengeAppMemberPuzzle> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<ChallengeAppMemberPuzzle> findAll() {
        return null;
    }

    @Override
    public Iterable<ChallengeAppMemberPuzzle> findAllById(Iterable<Long> longs) {
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
    public void delete(ChallengeAppMemberPuzzle entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends ChallengeAppMemberPuzzle> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
