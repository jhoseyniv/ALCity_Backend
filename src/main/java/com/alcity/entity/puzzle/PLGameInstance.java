package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.appmember.PLObjectiveTransaction;
import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.learning.LearningSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

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

    @OneToMany(mappedBy = "gameInstance", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<PLObjectiveTransaction> objectiveTransactions;

    public PLGameInstance(AppMember player, PuzzleLevel puzzleLevel, String startPlayTime, String endPlayTime, GameStatus gameStatus, byte[] analyticalData, Long playDuration
            ,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.startPlayTime = startPlayTime;
        this.endPlayTime = endPlayTime;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
        this.playDuration = playDuration;
    }
}
