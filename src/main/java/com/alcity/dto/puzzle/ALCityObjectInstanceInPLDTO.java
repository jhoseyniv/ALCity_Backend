package com.alcity.dto.puzzle;

public class ALCityObjectInstanceInPLDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Long createdById;

    private Long updatedByID;

    private Integer row;
    private Integer col;
    private Integer zOrder;
    private Long alCityObjectinPGId;
    private String alCityObjectinPGTitle;
    private String alCityObjectinPGCode ;

    public Long getAlCityObjectinPGId() {
        return alCityObjectinPGId;
    }

    public void setAlCityObjectinPGId(Long alCityObjectinPGId) {
        this.alCityObjectinPGId = alCityObjectinPGId;
    }

    public String getAlCityObjectinPGTitle() {
        return alCityObjectinPGTitle;
    }

    public void setAlCityObjectinPGTitle(String alCityObjectinPGTitle) {
        this.alCityObjectinPGTitle = alCityObjectinPGTitle;
    }

    public String getAlCityObjectinPGCode() {
        return alCityObjectinPGCode;
    }

    public void setAlCityObjectinPGCode(String alCityObjectinPGCode) {
        this.alCityObjectinPGCode = alCityObjectinPGCode;
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

    public ALCityObjectInstanceInPLDTO() {
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

    public ALCityObjectInstanceInPLDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedByID,
                                       Integer row, Integer col, Integer zOrder, Long alCityObjectinPGId, String alCityObjectinPGTitle, String alCityObjectinPGCode) {
        this.id = id;
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
        this.alCityObjectinPGId = alCityObjectinPGId;
        this.alCityObjectinPGTitle = alCityObjectinPGTitle;
        this.alCityObjectinPGCode = alCityObjectinPGCode;
    }
}
