package com.alcity.dto.puzzle;

public class PLGroundDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Integer numRows;
    private Integer numColumns;

    private Integer xPosition;
    private Integer yPosition;
    private Integer zPosition;
    private Integer xRotation;
    private Integer yRotation;
    private Integer zRotation;
    private Long puzzleLevelId;

    private String puzzleLevelTitle;


    private byte[] boardGraphic;

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



    public byte[] getBoardGraphic() {
        return boardGraphic;
    }

    public void setBoardGraphic(byte[] boardGraphic) {
        this.boardGraphic = boardGraphic;
    }


    public PLGroundDTO() {
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

    public PLGroundDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                       Integer numRows, Integer numColumns, Integer xPosition,Integer yPosition,Integer zPosition,Integer xRotation,Integer yRotation ,Integer zRotation,
                       Long puzzleLevelId, String puzzleLevelTitle,
                       byte[] boardGraphic) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.numRows = numRows;
        this.numColumns = numColumns;
        this.xPosition= xPosition;
        this.yPosition = yPosition;
        this.zPosition = zPosition;
        this.xRotation = xRotation;
        this.yRotation = yRotation;
        this.zRotation = zRotation;

        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.boardGraphic = boardGraphic;
    }
}
