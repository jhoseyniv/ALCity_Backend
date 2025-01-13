package com.alcity.service.play;


import com.alcity.dto.appmember.AppMemberJourneyDTO;
import com.alcity.dto.player.PlayHistoryDTO;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.play.PlayHistory;
import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.repository.play.PlayHistoryRepository;
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

    public Integer getPuzzleLevelStars(Float score,Float first,Float two,Float three) {
        if(score >= first) return  1;
        if(score >= two) return  2;
        if(score >= three) return  3;
        return 1;
    }

    public PlayHistoryDTO getPlayHistory(PlayHistory history){
        PlayHistoryDTO dto = new PlayHistoryDTO();
        dto.setId(history.getId());
        dto.setPlayScore(history.getPlayScore());
        dto.setPlayTime(history.getPlayTime());
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
        dto.setStars(getPuzzleLevelStars(history.getPlayScore(),pl.getFirstStarScore(),pl.getSecondStarScore(),pl.getThirdStartScore()));

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

    @Override
    public <S extends PlayHistory> Iterable<S> saveAll(Iterable<S> entities) {
        return null;
    }

    @Override
    public Optional<PlayHistory> findById(Long id) {
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
