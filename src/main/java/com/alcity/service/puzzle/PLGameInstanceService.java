package com.alcity.service.puzzle;


import com.alcity.dto.puzzle.PLDTO;
import com.alcity.dto.puzzle.PLEventDTO;
import com.alcity.dto.puzzle.PLGameInstanceDTO;
import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.puzzle.PLGameInstance;
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
import java.util.Collection;
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
        PLGameInstance  gameInstance = new PLGameInstance(appMemberOptional.get(),puzzleLevelOptional.get(), DateUtils.getNow(),null,gameStatus,1L,DateUtils.getNow(),DateUtils.getNow(),appMemberOptional.get(),appMemberOptional.get());
        plGameInstanceRepository.save(gameInstance);
        PLGameInstanceDTO instanceDTO = DTOUtil.getPLGameInstanceDTO(gameInstance);
        return instanceDTO;
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
    public Iterable<PLGameInstance> findAllById(Iterable<Long> longs) {
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
    public void delete(PLGameInstance entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PLGameInstance> entities) {

    }

    @Override
    public void deleteAll() {

    }
}
