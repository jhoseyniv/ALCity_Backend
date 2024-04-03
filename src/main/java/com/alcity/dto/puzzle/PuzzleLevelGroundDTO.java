package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;
import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.puzzle.PuzzleLevel;

public class PuzzleLevelGroundDTO extends BaseTableDTO {

    private Integer numRows;
    private Integer numColumns;
    private String cameraSetup;
    private BinaryContentDTO boardGraphic;
   // private PuzzleLevelDTO puzzleLevelDTO;


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

    public BinaryContentDTO getBoardGraphic() {
        return boardGraphic;
    }

    public void setBoardGraphic(BinaryContentDTO boardGraphic) {
        this.boardGraphic = boardGraphic;
    }

    public PuzzleLevelGroundDTO() {
    }

    public PuzzleLevelGroundDTO(Long id, Long version, String created, String updated, Integer numRows, Integer numColumns, String cameraSetup, BinaryContentDTO boardGraphic) {
        super(id, version, created, updated);
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cameraSetup = cameraSetup;
        this.boardGraphic = boardGraphic;
    }
}
