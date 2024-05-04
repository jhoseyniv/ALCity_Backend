package com.alcity.entity.play;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PuzzleLevel;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;


@Entity
public class PermitedPlayer extends BaseTable {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "player_id", referencedColumnName = "id")
    @JsonProperty("playerId")
    private ApplicationMember player;

    @ManyToOne(fetch = FetchType.EAGER, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    public PermitedPlayer() {
    }

    public ApplicationMember getPlayer() {
        return player;
    }

    public void setPlayer(ApplicationMember player) {
        this.player = player;
    }

    public PermitedPlayer(ApplicationMember player, PuzzleLevel puzzleLevel, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.player = player;
        this.puzzleLevel = puzzleLevel;
    }
}
