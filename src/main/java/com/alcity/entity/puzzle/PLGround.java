package com.alcity.entity.puzzle;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.CameraSetup;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;

@Entity
public class PLGround extends BaseTable {

    @Column(name="numRows")
    private Integer numRows;

    @Column(name="numColumns")
    private Integer numColumns;

    @ManyToOne(fetch = FetchType.LAZY, optional = true,cascade = CascadeType.ALL)
    @JoinColumn(name = "camera_setup_id", nullable = true)
    @JsonIgnore
    private CameraSetup cameraSetup;

    public CameraSetup getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetup cameraSetup) {
        this.cameraSetup = cameraSetup;
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

    public PLGround() {
    }

    public PLGround(Integer numRows, Integer numColumns, PuzzleLevel puzzleLevel, BinaryContent boardGraphic, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.puzzleLevel = puzzleLevel;
        this.boardGraphic = boardGraphic;
    }
}
