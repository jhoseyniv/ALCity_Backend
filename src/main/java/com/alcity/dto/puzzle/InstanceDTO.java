package com.alcity.dto.puzzle;

public class InstanceDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Long createdById;

    private Long updatedByID;

    private String name;

    private Integer row;
    private Integer col;
    private Integer zOrder;
    private Long PGObjectId;
    private String PGObjectTitle;
    private String PGObjectCode ;
    private Long puzzleLevelId;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPGObjectId() {
        return PGObjectId;
    }

    public void setPGObjectId(Long PGObjectId) {
        this.PGObjectId = PGObjectId;
    }

    public String getPGObjectTitle() {
        return PGObjectTitle;
    }

    public void setPGObjectTitle(String PGObjectTitle) {
        this.PGObjectTitle = PGObjectTitle;
    }

    public String getPGObjectCode() {
        return PGObjectCode;
    }

    public void setPGObjectCode(String PGObjectCode) {
        this.PGObjectCode = PGObjectCode;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
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

    public InstanceDTO() {
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedByID() {
        return updatedByID;
    }

    public void setUpdatedByID(Long updatedByID) {
        this.updatedByID = updatedByID;
    }

    public InstanceDTO(Long id,String name, Integer row, Integer col, Integer zOrder, Long PGObjectId, String PGObjectTitle, String PGObjectCode, Long puzzleLevelId,
                         Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedByID) {
        this.id = id;
        this.name = name;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedByID = updatedByID;
        this.row = row;
        this.col = col;
        this.zOrder = zOrder;
        this.PGObjectId = PGObjectId;
        this.PGObjectTitle = PGObjectTitle;
        this.PGObjectCode = PGObjectCode;
        this.puzzleLevelId = puzzleLevelId;
    }
}
