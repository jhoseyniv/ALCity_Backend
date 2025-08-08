package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;



@Table(name = "alcity_instance_inpl")

@Entity
public class Instance extends BaseTable implements Serializable {

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
    private PGObject alCityObjectInPG;

    public PGObject getAlCityObjectInPG() {
        return alCityObjectInPG;
    }

    public void setAlCityObjectInPG(PGObject alCityObjectInPG) {
        this.alCityObjectInPG = alCityObjectInPG;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "pl_cell_id", nullable = true)
    @JsonIgnore
    private PLCell plCell;

    public PLCell getPlCell() {
        return plCell;
    }

    public void setPlCell(PLCell plCell) {
        this.plCell = plCell;
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

    public Instance() {
    }

    public Instance(String name, Integer row, Integer col, Integer zOrder, PLCell plCell, PGObject alCityObjectInPG, PuzzleLevel puzzleLevel, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.plCell = plCell;
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
        this.alCityObjectInPG = alCityObjectInPG;
        this.puzzleLevel = puzzleLevel;
    }
}
