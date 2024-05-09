package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class ALCityInstanceInPL extends BaseTable implements Serializable {

    @Column(name="name")
    private String name;

    @Column(name="row")
    private Integer row;

    @Column(name="col")
    private Integer col;

    @Column(name="zOrder")
    private Integer zOrder;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "alcity_Object_In_PG_id", nullable = false)
    @JsonIgnore
    private ALCityObjectInPG alCityObjectInPG;

    public ALCityObjectInPG getAlCityObjectInPG() {
        return alCityObjectInPG;
    }

    public void setAlCityObjectInPG(ALCityObjectInPG alCityObjectInPG) {
        this.alCityObjectInPG = alCityObjectInPG;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    public PuzzleLevel getPuzzleLevel() {
        return puzzleLevel;
    }

    public void setPuzzleLevel(PuzzleLevel puzzleLevel) {
        this.puzzleLevel = puzzleLevel;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ALCityInstanceInPL() {
    }

    public ALCityInstanceInPL(String name, Integer row, Integer col, Integer zOrder, ALCityObjectInPG alCityObjectInPG, PuzzleLevel puzzleLevel, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
        this.alCityObjectInPG = alCityObjectInPG;
        this.puzzleLevel = puzzleLevel;
    }
}
