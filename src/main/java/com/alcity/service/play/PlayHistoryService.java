package com.alcity.service.play;


import com.alcity.dto.appmember.AppMemberJourneyDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.dto.puzzle.PLDTO;
import com.alcity.entity.alenum.PLDifficulty;
import com.alcity.entity.alenum.PLStatus;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PLPrivacy;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.appmember.AppMemberRepository;
import com.alcity.repository.play.PlayHistoryRepository;
import com.alcity.service.puzzle.PGService;
import com.alcity.service.puzzle.PuzzleLevelService;
import com.alcity.utility.DateUtils;
import com.alcity.utility.ToolBox;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;;
import java.util.Collection;
import java.util.Optional;

@Service
@Transactional
public class PlayHistoryService implements PlayHistoryRepository {

    @Autowired
    PlayHistoryRepository playHistoryRepository;

    @Autowired
    private AppMemberRepository appMemberRepository;

    @Autowired
    private PuzzleLevelService puzzleLevelService;
    @Autowired
    private PGService pgService;

    public PlayHistoryDTO getPlayHistory(PlayHistory history){
        PlayHistoryDTO dto = new PlayHistoryDTO();
        dto.setId(history.getId());
        dto.setPlayScore(history.getPlayScore());
        dto.setStartPlayTime(history.getStartPlayTime());
        dto.setEndPlayTime(history.getEndPlayTime());
        dto.setPlayDuration(history.getPlayDuration());
        //get player information
        AppMember player = history.getPlayer();
        dto.setPlayerId(player.getId());
        dto.setPlayerUsername(player.getUsername());

        //get Puzzle Level information
        PuzzleLevel pl = history.getPuzzleLevel();
        dto.setPlId(pl.getId());
        dto.setPlCode(pl.getCode());
        dto.setPlTitle(pl.getTitle());
        dto.setPlMaxScore(pl.getMaxScore());
        dto.setStars(ToolBox.getPuzzleLevelStars(history.getPlayScore(),pl.getFirstStarScore(),pl.getSecondStarScore(),pl.getThirdStartScore()));

        //get Puzzle Group information
        PuzzleGroup pg = pl.getPuzzleGroup();
        dto.setPgId(pg.getId());
        dto.setPgTitle(pg.getTitle());



        return dto;
    }
        @Override
    public <S extends PlayHistory> S save(S entity) {
        return playHistoryRepository.save(entity);
    }
    public PlayHistory save(PlayHistoryDTO dto, String code) {
        Optional<AppMember> createdBy = appMemberRepository.findByUsername("admin");
        PlayHistory playHistory=null;
        Optional<PuzzleLevel> puzzleLevelOptional =  puzzleLevelService.findById(dto.getPlId());
        Optional<PuzzleGroup> puzzleGroupOptional =  pgService.findById(dto.getPgId());
        Optional<AppMember> playerOptional = appMemberRepository.findById(dto.getPlayerId());

        if(puzzleLevelOptional.isEmpty() || puzzleGroupOptional.isEmpty() || playerOptional.isEmpty()) return null;


       if (code.equalsIgnoreCase("Save")) { //Save
           playHistory = new PlayHistory(playerOptional.get(),puzzleLevelOptional.get(), dto.getStartPlayTime(), dto.getEndPlayTime(),
                   dto.getPlayDuration(),dto.getPlayScore(),dto.getStars(),
                   String.valueOf(dto.getAnalyticalData()).getBytes(), 1L, DateUtils.getNow(), DateUtils.getNow(), createdBy.get(), createdBy.get());
            playHistoryRepository.save(playHistory);
        }else{//edit
            Optional<PlayHistory> playHistoryOptional= playHistoryRepository.findById(dto.getId());
            if(playHistoryOptional.isPresent()) {
                playHistory = playHistoryOptional.get();
                playHistory.setStars(dto.getStars());
                playHistory.setPuzzleLevel(puzzleLevelOptional.get());
                playHistory.setPlayer(playerOptional.get());
                playHistory.setPlayScore(dto.getPlayScore());
                playHistory.setPlayDuration(dto.getPlayDuration());
                playHistory.setStartPlayTime(dto.getStartPlayTime());
                playHistory.setEndPlayTime(dto.getEndPlayTime());
                playHistoryRepository.save(playHistory);
            }
        }


        return playHistory;
    }
    @Override
    public <S extends PlayHistory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PlayHistory> findById(Long id) {
        if(id==null) return Optional.empty();
        return playHistoryRepository.findById(id);
    }

    @Override
    public boolean existsById(Long aLong) {
        return false;
    }

    @Override
    public Collection<PlayHistory> findAll() {
        return null;
    }

    @Override
    public Iterable<PlayHistory> findAllById(Iterable<Long> longs) {
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
    public void delete(PlayHistory entity) {

    }

    @Override
    public void deleteAllById(Iterable<? extends Long> longs) {

    }

    @Override
    public void deleteAll(Iterable<? extends PlayHistory> entities) {

    }

    @Override
    public void deleteAll() {

    }

    @Override
    public Collection<PlayHistory> findByplayScore(Float score) {
        return null;
    }
}
