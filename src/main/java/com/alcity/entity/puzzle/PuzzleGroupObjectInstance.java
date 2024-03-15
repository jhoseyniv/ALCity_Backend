package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PuzzleGroupObjectInstance extends BaseTable implements Serializable {

    @Column(name="row")
    private Integer row;

    @Column(name="col")
    private Integer col;

    @Column(name="zOrder")
    private Integer zOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_group_puzzle_object_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup_PuzzleObject puzzleGroup_PuzzleObject;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;



    public PuzzleGroupObjectInstance() {
    }

    public PuzzleGroupObjectInstance( Integer row, Integer col, Integer zOrder, PuzzleGroup_PuzzleObject puzzleGroup_PuzzleObject, PuzzleLevel puzzleLevel,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
        this.puzzleGroup_PuzzleObject = puzzleGroup_PuzzleObject;
        this.puzzleLevel = puzzleLevel;
    }
}
