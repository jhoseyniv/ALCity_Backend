package com.alcity.entity.puzzle;

import com.alcity.entity.base.GameStatus;
import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class PuzzleLevelGameInstance extends RecordInformation implements Serializable {


    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonProperty("playerId")
    private ApplicationMember player;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "game_status_id", nullable = true)
    @JsonIgnore
    private GameStatus gameStatus;

    public PuzzleLevelGameInstance() {
    }

    public PuzzleLevelGameInstance(ApplicationMember player, PuzzleLevel puzzleLevel, GameStatus gameStatus,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.gameStatus = gameStatus;
    }
}
