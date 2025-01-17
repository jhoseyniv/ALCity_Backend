package com.alcity.entity.play;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;

@Entity
public class PlayHistory extends BaseTable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonProperty("playerId")
    private AppMember player;

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
    @Column(name="stars")
    private Integer stars;

    public AppMember getPlayer() {
        return player;
    }

    public void setPlayer(AppMember player) {
        this.player = player;
    }

    public PuzzleLevel getPuzzleLevel() {
        return puzzleLevel;
    }

    public void setPuzzleLevel(PuzzleLevel puzzleLevel) {
        this.puzzleLevel = puzzleLevel;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public Integer getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(Integer playDuration) {
        this.playDuration = playDuration;
    }

    public Float getPlayScore() {
        return playScore;
    }

    public void setPlayScore(Float playScore) {
        this.playScore = playScore;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public PlayHistory() {
    }

    public PlayHistory(AppMember player, PuzzleLevel puzzleLevel, String playTime, Integer playDuration, Float playScore,Integer stars, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.playTime = playTime;
        this.playDuration = playDuration;
        this.playScore = playScore;
        this.stars = stars;
    }
}
