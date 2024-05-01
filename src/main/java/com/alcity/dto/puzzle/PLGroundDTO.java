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
    private CameraSetupDTO cameraSetup;
    private BinaryContentDTO boardGraphic;


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

    public PLGroundDTO() {
    }

    public PLGroundDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                       Integer numRows, Integer numColumns, CameraSetupDTO cameraSetup, BinaryContentDTO boardGraphic) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.cameraSetup = cameraSetup;
        this.boardGraphic = boardGraphic;
    }
}
