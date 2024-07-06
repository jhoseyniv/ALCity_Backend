package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class PLGameInstance extends BaseTable implements Serializable {


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonProperty("playerId")
    private AppMember player;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @Enumerated(EnumType.ORDINAL)
    private GameStatus gameStatus;

    public PLGameInstance() {
    }

    public PLGameInstance(AppMember player, PuzzleLevel puzzleLevel, GameStatus gameStatus, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.gameStatus = gameStatus;
    }
}
