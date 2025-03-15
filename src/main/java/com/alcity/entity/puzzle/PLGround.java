package com.alcity.entity.puzzle;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class PLGround extends BaseTable {

    @Column(name="numRows")
    private Integer numRows;

    @Column(name="numColumns")
    private Integer numColumns;

    @Column(name="xPosition")
    private Integer xPosition;

    @Column(name="yPosition")
    private Integer yPosition;

    @Column(name="zPosition")
    private Integer zPosition;

    @Column(name="xRotation")
    private Integer xRotation;

    @Column(name="yRotation")
    private Integer yRotation;

    @Column(name="zRotation")
    private Integer zRotation;



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

    public Integer getxPosition() {
        return xPosition;
    }

    public void setxPosition(Integer xPosition) {
        this.xPosition = xPosition;
    }

    public Integer getyPosition() {
        return yPosition;
    }

    public void setyPosition(Integer yPosition) {
        this.yPosition = yPosition;
    }

    public Integer getzPosition() {
        return zPosition;
    }

    public void setzPosition(Integer zPosition) {
        this.zPosition = zPosition;
    }

    public Integer getxRotation() {
        return xRotation;
    }

    public void setxRotation(Integer xRotation) {
        this.xRotation = xRotation;
    }

    public Integer getyRotation() {
        return yRotation;
    }

    public void setyRotation(Integer yRotation) {
        this.yRotation = yRotation;
    }

    public Integer getzRotation() {
        return zRotation;
    }

    public void setzRotation(Integer zRotation) {
        this.zRotation = zRotation;
    }

    public PLGround() {
    }

    public PLGround(Integer numRows, Integer numColumns, Integer xPosition,Integer yPosition,Integer zPosition,Integer xRotation,Integer yRotation ,Integer zRotation,
                    PuzzleLevel puzzleLevel, byte[] boardGraphic,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.xPosition= xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;
        this.puzzleLevel = puzzleLevel;
        this.boardGraphic = boardGraphic;
    }
}
