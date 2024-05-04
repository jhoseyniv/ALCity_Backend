package com.alcity.entity.play;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class PlayHistory extends BaseTable {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonProperty("playerId")
    private ApplicationMember player;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @Column(name="playTime")
    private String playTime;

    @Column(name="playDuration")
    private Integer playDuration;

    @Column(name="playScore")
    private Float playScore;


    public PlayHistory() {
    }

    public PlayHistory(ApplicationMember player, PuzzleLevel puzzleLevel, String playTime, Integer playDuration, Float playScore,Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.playTime = playTime;
        this.playDuration = playDuration;
        this.playScore = playScore;
    }
}
