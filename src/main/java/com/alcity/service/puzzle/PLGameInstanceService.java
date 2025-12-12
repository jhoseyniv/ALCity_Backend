package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLEndPlayDTO;
import com.alcity.dto.puzzle.PLStartPlayDTO;
import com.alcity.dto.puzzle.PLGameInstanceDTO;
import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.appmember.EnergyConfig;
import com.alcity.entity.appmember.ObjectiveTransaction;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.puzzle.PLGameInstanceRepository;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.service.appmember.ObjectiveTransactionService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.time.ZonedDateTime;
import java.util.*;

@Service
@Transactional
public class PLGameInstanceService implements PLGameInstanceRepository {

    @Autowired
    @Qualifier("PLGameInstanceRepository")
    PLGameInstanceRepository plGameInstanceRepository;

    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private ObjectiveTransactionService objectiveTransactionService;

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @Override
    public <S extends PLGameInstance> S save(S entity) {
        return plGameInstanceRepository.save(entity);
    }
    @Transactional
    public PLGameInstanceDTO startGameInstance(PLStartPlayDTO plEventDTO) {

        Optional<AppMember> appMemberOptional = appMemberService.findById(plEventDTO.getAppMemberId());
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(plEventDTO.getPuzzleLevelId());
        Integer refiilEnergyTime = 40;
        if(appMemberOptional.isEmpty() || puzzleLevelOptional.isEmpty()){ return null;}
        AppMember member = appMemberOptional.get();
        EnergyConfig energyConfig = member.getEnergyConfig();
        if(energyConfig != null)
            refiilEnergyTime = energyConfig.getTimeToRefill();

        // decrease one unit energy  after start a game by user
        member.setEnergy(member.getEnergy() - 1);
        ZonedDateTime now = ZonedDateTime.now();
        if (member.getRefillEnergyExpirationTime() == null || member.getRefillEnergyExpirationTime().isBefore(now)) {
            member.setRefillEnergyExpirationTime(now.plusMinutes(refiilEnergyTime)); // مقدار دلخواه شما برای ریکاوری انرژی
        }

        PLGameInstance  gameInstance = new PLGameInstance(appMemberOptional.get(),puzzleLevelOptional.get(), DateUtils.getNow(),"",GameStatus.Playing,null,0L,
                1L,DateUtils.getNow(),DateUtils.getNow(),appMemberOptional.get(),appMemberOptional.get());
        plGameInstanceRepository.save(gameInstance);
        appMemberService.save(member);
        return DTOUtil.getGameInstanceDTO(gameInstance);
    }


    public PLGameInstanceDTO updateGameInstanceStatus(PLEndPlayDTO endPlayDTO) {
        Optional<PLGameInstance> plGameInstanceOptional = plGameInstanceRepository.findById(endPlayDTO.getInstanceId());
        PLGameInstance gameInstance = plGameInstanceOptional.get();
        GameStatus gameStatus = GameStatus.getByTitle(endPlayDTO.getGameStatus());
        gameInstance.setGameStatus(gameStatus);
        gameInstance.setAnalyticalData(endPlayDTO.getAnalyticalData());
        plGameInstanceRepository.save(gameInstance);
        return DTOUtil.getGameInstanceDTO(gameInstance);
    }

    @Override
    public <S extends PLGameInstance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLGameInstance> findById(Long id) {
        if(id == null) { return Optional.empty(); }
        return plGameInstanceRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PLGameInstance> findAll() {
        return null;
    }

    @Override
    public Collection<PLGameInstance> findByPlayerAndPuzzleLevel(AppMember player, PuzzleLevel puzzleLevel) {
        return plGameInstanceRepository.findByPlayerAndPuzzleLevel(player, puzzleLevel);
    }

    @Override
    public Collection<PLGameInstance> findByPlayer(AppMember player) {
        return plGameInstanceRepository.findByPlayer(player);
    }

    @Override
    public Collection<PLGameInstance> findByPuzzleLevel(PuzzleLevel puzzleLevel) {
        return plGameInstanceRepository.findByPuzzleLevel(puzzleLevel);
    }

    @Override
    public Iterable<PLGameInstance> findAllById(Iterable<Long> longs) {
        return null;
    }

    @Override
    public long count() {
        return 0;
    }

    @Override
    public void deleteById(Long aLong) {
        plGameInstanceRepository.deleteById(aLong);
    }

    @Override
    public void delete(PLGameInstance entity) {
        plGameInstanceRepository.delete(entity);
    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }







    @Override
    public void deleteAll(Iterable<? extends PLGameInstance> entities) {
        Iterator<PLGameInstance> iterator = (Iterator<PLGameInstance>) entities.iterator();
        while(iterator.hasNext()) {
            PLGameInstance gameInstance = iterator.next();
            Collection<ObjectiveTransaction> objectiveTransactions = objectiveTransactionService.findByGameInstance(gameInstance);
            objectiveTransactionService.deleteAll(objectiveTransactions);
        }

        plGameInstanceRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }
}
