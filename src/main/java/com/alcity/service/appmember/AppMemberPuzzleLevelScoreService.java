package com.alcity.service.appmember;


import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.AppMemberPuzzleLevelScore;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberPuzzleLevelScoreRepository;
import com.alcity.repository.appmember.PLObjectiveTransactionRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional

public class AppMemberPuzzleLevelScoreService implements AppMemberPuzzleLevelScoreRepository {

    @Autowired
    AppMemberPuzzleLevelScoreRepository appMemberPuzzleLevelScoreRepository;


    @Override
    public <S extends AppMemberPuzzleLevelScore> S save(S entity) {
        return null;
    }

    @Override
    public <S extends AppMemberPuzzleLevelScore> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<AppMemberPuzzleLevelScore> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public Collection<AppMemberPuzzleLevelScore> findByPlayer(AppMember player) {
        return appMemberPuzzleLevelScoreRepository.findByPlayer(player);
    }

    @Override
    public Optional<AppMemberPuzzleLevelScore> findByPuzzleLevelAndPlayer(PuzzleLevel puzzleLevel, AppMember player) {
        return appMemberPuzzleLevelScoreRepository.findByPuzzleLevelAndPlayer(puzzleLevel, player);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Iterable<AppMemberPuzzleLevelScore> findAll() {
        return null;
    }

    @Override
    public Iterable<AppMemberPuzzleLevelScore> findAllById(Iterable<Long> longs) {
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
    public void delete(AppMemberPuzzleLevelScore entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends AppMemberPuzzleLevelScore> entities) {

    }

    @Override
    public void deleteAll() {

    }



}
