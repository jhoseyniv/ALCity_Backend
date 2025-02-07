package com.alcity.dto.puzzle;

import com.alcity.dto.CameraSetupDTO;
import com.alcity.dto.base.BinaryContentDTO;

public class PLGroundDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Integer numRows;
    private Integer numColumns;

    private Long puzzleLevelId;

    private String puzzleLevelTitle;

    private Long cameraSetupId;
    private String cameraSetupTitle;

    private Long boardGraphicId;
    private String boardGraphicName;
    private String boardGraphicFileType;

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



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public String getPuzzleLevelTitle() {
        return puzzleLevelTitle;
    }

    public void setPuzzleLevelTitle(String puzzleLevelTitle) {
        this.puzzleLevelTitle = puzzleLevelTitle;
    }

    public Long getCameraSetupId() {
        return cameraSetupId;
    }

    public void setCameraSetupId(Long cameraSetupId) {
        this.cameraSetupId = cameraSetupId;
    }

    public String getCameraSetupTitle() {
        return cameraSetupTitle;
    }

    public void setCameraSetupTitle(String cameraSetupTitle) {
        this.cameraSetupTitle = cameraSetupTitle;
    }

    public Long getBoardGraphicId() {
        return boardGraphicId;
    }

    public void setBoardGraphicId(Long boardGraphicId) {
        this.boardGraphicId = boardGraphicId;
    }

    public String getBoardGraphicName() {
        return boardGraphicName;
    }

    public void setBoardGraphicName(String boardGraphicName) {
        this.boardGraphicName = boardGraphicName;
    }

    public String getBoardGraphicFileType() {
        return boardGraphicFileType;
    }

    public void setBoardGraphicFileType(String boardGraphicFileType) {
        this.boardGraphicFileType = boardGraphicFileType;
    }

    public PLGroundDTO() {
    }

    public PLGroundDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                       Integer numRows, Integer numColumns, Long puzzleLevelId, String puzzleLevelTitle, Long cameraSetupId, String cameraSetupTitle,
                       Long boardGraphicId ,String boardGraphicFileName, String boardGraphicFileType) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.cameraSetupId = cameraSetupId;
        this.cameraSetupTitle = cameraSetupTitle;
        this.boardGraphicId = boardGraphicId;
        this.boardGraphicName = boardGraphicFileName;
        this.boardGraphicFileType = boardGraphicFileType;
    }
}
