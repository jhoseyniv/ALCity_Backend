package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Table(uniqueConstraints={
        @UniqueConstraint(columnNames = {"application_member_id", "puzzle_level_id"})
})
@Entity
public class AppMemberPuzzleLevelScore extends BaseTable implements Serializable {
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember player;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_id", referencedColumnName = "id")
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @Column(name="scoreByBaseCurrency")
    private Float scoreByBaseCurrency;

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

    public Float getScoreByBaseCurrency() {
        return scoreByBaseCurrency;
    }

    public void setScoreByBaseCurrency(Float scoreByBaseCurrency) {
        this.scoreByBaseCurrency = scoreByBaseCurrency;
    }

    public AppMemberPuzzleLevelScore() {
    }

    public AppMemberPuzzleLevelScore(AppMember player, PuzzleLevel puzzleLevel, Float scoreByBaseCurrency ,
                                     Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
        this.scoreByBaseCurrency = scoreByBaseCurrency;
    }
}
