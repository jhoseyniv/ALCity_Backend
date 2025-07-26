package com.alcity.entity.puzzle;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class PLGround extends BaseTable {

    @Column(name="numRows")
    private Integer numRows;

    @Column(name="numColumns")
    private Integer numColumns;

    @Column(name="xPosition")
    private Float xPosition;

    @Column(name="yPosition")
    private Float yPosition;

    @Column(name="zPosition")
    private Float zPosition;

    @Column(name="xRotation")
    private Float xRotation;

    @Column(name="yRotation")
    private Float yRotation;

    @Column(name="zRotation")
    private Float zRotation;

    @Column(name="zoom")
    private Boolean zoom;

    @Column(name="pan")
    private Boolean pan;

    @Column(name="rotation")
    private Boolean rotation;


    @OneToMany(mappedBy = "plGround", fetch = FetchType.LAZY)
    @JsonIgnore
    private Collection<PLCell> plCells;


    public Collection<PLCell> getPlCells() {
        return plCells;
    }

    public void setPlCells(Collection<PLCell> plCells) {
        this.plCells = plCells;
    }


    public Integer getNumRows() {
        return numRows;
    }

    public void setNumRows(Integer numRows) {
        this.numRows = numRows;
    }

    public Integer getNumColumns() {
        return numColumns;
    }

    public void setNumColumns(Integer numColumns) {
        this.numColumns = numColumns;
    }


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    public PuzzleLevel getPuzzleLevel() {
        return puzzleLevel;
    }

    public void setPuzzleLevel(PuzzleLevel puzzleLevel) {
        this.puzzleLevel = puzzleLevel;
    }

    @Lob
    @Basic(fetch = FetchType.LAZY)
    private byte[] boardGraphic;

    public byte[] getBoardGraphic() {
        return boardGraphic;
    }

    public void setBoardGraphic(byte[] boardGraphic) {
        this.boardGraphic = boardGraphic;
    }

    public Float getxPosition() {
        return xPosition;
    }

    public void setxPosition(Float xPosition) {
        this.xPosition = xPosition;
    }

    public Float getyPosition() {
        return yPosition;
    }

    public void setyPosition(Float yPosition) {
        this.yPosition = yPosition;
    }

    public Float getzPosition() {
        return zPosition;
    }

    public void setzPosition(Float zPosition) {
        this.zPosition = zPosition;
    }

    public Float getxRotation() {
        return xRotation;
    }

    public void setxRotation(Float xRotation) {
        this.xRotation = xRotation;
    }

    public Float getyRotation() {
        return yRotation;
    }

    public void setyRotation(Float yRotation) {
        this.yRotation = yRotation;
    }

    public Float getzRotation() {
        return zRotation;
    }

    public void setzRotation(Float zRotation) {
        this.zRotation = zRotation;
    }

    public Boolean getZoom() {
        return zoom;
    }

    public void setZoom(Boolean zoom) {
        this.zoom = zoom;
    }

    public Boolean getPan() {
        return pan;
    }

    public void setPan(Boolean pan) {
        this.pan = pan;
    }

    public Boolean getRotation() {
        return rotation;
    }

    public void setRotation(Boolean rotation) {
        this.rotation = rotation;
    }

    public PLGround() {
    }

    public PLGround(Integer numRows, Integer numColumns, Float xPosition,Float yPosition,Float zPosition,Float xRotation,Float yRotation ,Float zRotation,
                     Boolean zoom , Boolean pan,Boolean rotation,PuzzleLevel puzzleLevel, byte[] boardGraphic,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.xPosition= xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
        this.zoom =zoom;
        this.pan = pan;
        this.rotation = rotation;
        this.puzzleLevel = puzzleLevel;
        this.boardGraphic = boardGraphic;
    }
}
