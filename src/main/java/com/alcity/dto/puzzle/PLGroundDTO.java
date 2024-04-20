package com.alcity.dto.puzzle;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.base.BinaryContentDTO;

public class PLGroundDTO extends BaseTableDTO {

    private Integer numRows;
    private Integer numColumns;
    private CameraSetupDTO cameraSetup;
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

    public CameraSetupDTO getCameraSetup() {
        return cameraSetup;
    }

    public void setCameraSetup(CameraSetupDTO cameraSetup) {
        this.cameraSetup = cameraSetup;
    }

    public BinaryContentDTO getBoardGraphic() {
        return boardGraphic;
    }

    public void setBoardGraphic(BinaryContentDTO boardGraphic) {
        this.boardGraphic = boardGraphic;
    }

    public PLGroundDTO() {
    }

    public PLGroundDTO(Long id, Long version, String created, String updated, Integer numRows, Integer numColumns, String cameraSetup, BinaryContentDTO boardGraphic) {
        super(id, version, created, updated);
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.boardGraphic = boardGraphic;
    }
}
