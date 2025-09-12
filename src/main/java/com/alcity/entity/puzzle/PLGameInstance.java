package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.learning.LearningSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
@Table(name="plgame_instance")
public class PLGameInstance extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonProperty("playerId")
    private AppMember player;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @Column(name="startPlayTime")
    private String startPlayTime;

    @Column(name="endPlayTime")
    private String endPlayTime;

    @Enumerated(EnumType.ORDINAL)
    private GameStatus gameStatus;

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] analyticalData;

    @Column(name="playDuration")
    private Long playDuration;

    @Column(name="stars")
    private Integer stars;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_skill_id", nullable = false)
    @JsonIgnore
    private LearningSkill learningSkill;

    @NotNull(message = "{amount.notempty}")
    @Column(name="skillAmount")
    private Float skillAmount;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "walletItem_id", nullable = false)
    @JsonIgnore
    private WalletItem walletItem;

    @NotNull(message = "{amount.notempty}")
    @Column(name="walletItemAmount")
    private Float walletItemAmount;

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

    public String getStartPlayTime() {
        return startPlayTime;
    }

    public void setStartPlayTime(String startPlayTime) {
        this.startPlayTime = startPlayTime;
    }

    public String getEndPlayTime() {
        return endPlayTime;
    }

    public void setEndPlayTime(String endPlayTime) {
        this.endPlayTime = endPlayTime;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public byte[] getAnalyticalData() {
        return analyticalData;
    }

    public void setAnalyticalData(byte[] analyticalData) {
        this.analyticalData = analyticalData;
    }

    public Long getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(Long playDuration) {
        this.playDuration = playDuration;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public LearningSkill getLearningSkill() {
        return learningSkill;
    }

    public void setLearningSkill(LearningSkill learningSkill) {
        this.learningSkill = learningSkill;
    }

    public Float getSkillAmount() {
        return skillAmount;
    }

    public void setSkillAmount(Float skillAmount) {
        this.skillAmount = skillAmount;
    }

    public WalletItem getWalletItem() {
        return walletItem;
    }

    public void setWalletItem(WalletItem walletItem) {
        this.walletItem = walletItem;
    }

    public Float getWalletItemAmount() {
        return walletItemAmount;
    }

    public void setWalletItemAmount(Float walletItemAmount) {
        this.walletItemAmount = walletItemAmount;
    }

    public PLGameInstance() {
    }

    public PLGameInstance(AppMember player, PuzzleLevel puzzleLevel, String startPlayTime, String endPlayTime, GameStatus gameStatus, byte[] analyticalData, Long playDuration, Integer stars, LearningSkill learningSkill, Float skillAmount, WalletItem walletItem, Float walletItemAmount,
                          Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.startPlayTime = startPlayTime;
        this.endPlayTime = endPlayTime;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
        this.playDuration = playDuration;
        this.stars = stars;
        this.learningSkill = learningSkill;
        this.skillAmount = skillAmount;
        this.walletItem = walletItem;
        this.walletItemAmount = walletItemAmount;
    }
}
