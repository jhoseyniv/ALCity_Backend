package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLEventDTO;
import com.alcity.dto.puzzle.PLGameInstanceDTO;
import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLGameInstance;
import com.alcity.entity.puzzle.PLObjective;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.puzzle.PLGameInstanceRepository;
import com.alcity.service.appmember.AppMemberService;
import com.alcity.utility.DTOUtil;
import com.alcity.utility.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PLGameInstanceService implements PLGameInstanceRepository {

    @Autowired
    @Qualifier("PLGameInstanceRepository")
    PLGameInstanceRepository plGameInstanceRepository;

    @Autowired
    private AppMemberService appMemberService;

    @Autowired
    private PuzzleLevelService puzzleLevelService;

    @Override
    public <S extends PLGameInstance> S save(S entity) {
        return plGameInstanceRepository.save(entity);
    }

    public PLGameInstanceDTO startGameInstance(PLEventDTO plEventDTO) {

        Optional<AppMember> appMemberOptional = appMemberService.findById(plEventDTO.getAppMemberId());
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(plEventDTO.getPuzzleLevelId());
        GameStatus gameStatus = GameStatus.getByTitle(plEventDTO.getEventType());
        if(appMemberOptional.isEmpty() || puzzleLevelOptional.isEmpty()){ return null;}
        Collection<PLObjective> objectives = puzzleLevelOptional.get().getPlObjectives();

        PLGameInstance  gameInstance = new PLGameInstance(appMemberOptional.get(),puzzleLevelOptional.get(), DateUtils.getNow(),null,gameStatus,null,0L,0,null,0f,null,0f,
                1L,DateUtils.getNow(),DateUtils.getNow(),appMemberOptional.get(),appMemberOptional.get());
        plGameInstanceRepository.save(gameInstance);
        PLGameInstanceDTO gameInstanceDTO = DTOUtil.getPLGameInstanceDTO(gameInstance);
        return gameInstanceDTO;
    }


    public PLGameInstanceDTO updateGameInstanceStatus(PLEventDTO plEventDTO) {
        Optional<AppMember> appMemberOptional = appMemberService.findById(plEventDTO.getAppMemberId());
        Optional<PuzzleLevel> puzzleLevelOptional = puzzleLevelService.findById(plEventDTO.getPuzzleLevelId());
        GameStatus gameStatus = GameStatus.getByTitle(plEventDTO.getEventType());
        Optional<PLGameInstance> plGameInstanceOptional = plGameInstanceRepository.findById(plEventDTO.getId());
        PLGameInstance plGameInstance = plGameInstanceOptional.get();
        plGameInstance.setGameStatus(gameStatus);
        plGameInstanceRepository.save(plGameInstance);
        PLGameInstanceDTO instanceDTO = DTOUtil.getPLGameInstanceDTO(plGameInstance);
        return  instanceDTO;
    }

    @Override
    public <S extends PLGameInstance> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PLGameInstance> findById(Long id) {
        if(id == null) { return Optional.empty(); }
        return Optional.empty();
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
        plGameInstanceRepository.deleteAll(entities);
    }

    @Override
    public void deleteAll() {

    }
}
