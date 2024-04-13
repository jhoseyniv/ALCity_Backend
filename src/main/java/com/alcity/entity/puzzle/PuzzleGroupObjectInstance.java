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

    public PuzzleGroup_PuzzleObject getPuzzleGroup_PuzzleObject() {
        return puzzleGroup_PuzzleObject;
    }

    public void setPuzzleGroup_PuzzleObject(PuzzleGroup_PuzzleObject puzzleGroup_PuzzleObject) {
        this.puzzleGroup_PuzzleObject = puzzleGroup_PuzzleObject;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    public Integer getRow() {
        return row;
    }

    public void setRow(Integer row) {
        this.row = row;
    }

    public Integer getCol() {
        return col;
    }

    public void setCol(Integer col) {
        this.col = col;
    }

    public Integer getzOrder() {
        return zOrder;
    }

    public void setzOrder(Integer zOrder) {
        this.zOrder = zOrder;
    }

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
