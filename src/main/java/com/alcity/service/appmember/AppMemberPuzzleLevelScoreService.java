package com.alcity.service.appmember;


import com.alcity.entity.appmember.*;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberPuzzleLevelScoreRepository;
import com.alcity.repository.appmember.PLObjectiveTransactionRepository;
import com.alcity.repository.appmember.WalletItemRespository;
import com.alcity.utility.DateUtils;
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

    @Autowired
    WalletItemService walletItemService;

    @Autowired
    WalletItemChangeRateService walletItemChangeRateService;


    @Override
    public <S extends AppMemberPuzzleLevelScore> S save(S entity) {
        return null;
    }

    public Float getScoreByBaseCurrency(WalletItem walletItem,Float amount) {
        if(walletItem.isBaseCurrency()) return amount;
        Optional<WalletItem> baseCurrencyOptional = walletItemService.findByBaseCurrency(true);
        if(baseCurrencyOptional.isEmpty()) return amount;
        Optional<WalletItemChangeRate>  changeRateOptional = walletItemChangeRateService.findByFromCurrencyAndToCurrency(walletItem,baseCurrencyOptional.get());
        Float changeRateAmount = changeRateOptional.get().getRate();
        return amount*changeRateAmount;
    }

    public void updateScores(PLObjectiveTransaction transaction) {
        AppMember appMember = transaction.getAppMember();
        WalletItem walletItem = transaction.getPlObjective().getWalletItem();
        Float amount = transaction.getAmount();
        PuzzleLevel puzzleLevel = transaction.getPlObjective().getPuzzleLevel();
        Float scoreByBaseCurrency = getScoreByBaseCurrency(walletItem,amount);
        Optional<AppMemberPuzzleLevelScore> appMemberPuzzleLevelScoreOptional = appMemberPuzzleLevelScoreRepository.findByPuzzleLevelAndPlayer(puzzleLevel, appMember);
        if(appMemberPuzzleLevelScoreOptional.isEmpty()){
            AppMemberPuzzleLevelScore puzzleLevelScore = new AppMemberPuzzleLevelScore(appMember,puzzleLevel,scoreByBaseCurrency,
                    1L, DateUtils.getNow(),DateUtils.getNow(),appMember,appMember);
            appMemberPuzzleLevelScoreRepository.save(puzzleLevelScore);
        }else{
            AppMemberPuzzleLevelScore puzzleLevelScore = appMemberPuzzleLevelScoreOptional.get();
            if(puzzleLevelScore.getScoreByBaseCurrency() < scoreByBaseCurrency) {
                puzzleLevelScore.setScoreByBaseCurrency(scoreByBaseCurrency);
                appMemberPuzzleLevelScoreRepository.save(puzzleLevelScore);
            }
        }
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
