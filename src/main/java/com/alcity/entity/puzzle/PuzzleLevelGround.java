package com.alcity.entity.puzzle;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
public class PuzzleLevelGround extends BaseTable {

    @Column(name="numRows")
    private Integer numRows;

    @Column(name="numColumns")
    private Integer numColumns;

    @Column(name="cameraSetup")
    private String cameraSetup;

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

    public String getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(String cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "board_graphic_id", nullable = true)
    @JsonIgnore
    private BinaryContent boardGraphic;

    public BinaryContent getBoardGraphic() {
        return boardGraphic;
    }

    public void setBoardGraphic(BinaryContent boardGraphic) {
        this.boardGraphic = boardGraphic;
    }

    public PuzzleLevelGround() {
    }

    public PuzzleLevelGround(Integer numRows, Integer numColumns, String cameraSetup, PuzzleLevel puzzleLevel, BinaryContent boardGraphic,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cameraSetup = cameraSetup;
        this.puzzleLevel = puzzleLevel;
        this.boardGraphic = boardGraphic;
    }
}
