package com.alcity.dto.puzzle;

import com.alcity.dto.base.BinaryContentDTO;

public class PuzzleObjectDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String title;
    private String objectCategory;

    private String code;

    private BinaryContentDTO picture;
    private BinaryContentDTO icon;

    public PuzzleObjectDTO() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObjectCategory() {
        return objectCategory;
    }

    public void setObjectCategory(String objectCategory) {
        this.objectCategory = objectCategory;
    }

    public BinaryContentDTO getPicture() {
        return picture;
    }

    public void setPicture(BinaryContentDTO picture) {
        this.picture = picture;
    }

    public BinaryContentDTO getIcon() {
        return icon;
    }

    public void setIcon(BinaryContentDTO icon) {
        this.icon = icon;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public PuzzleObjectDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String title, String objectCategory, String code, BinaryContentDTO picture, BinaryContentDTO icon) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.title = title;
        this.objectCategory = objectCategory;
        this.code = code;
        this.picture = picture;
        this.icon = icon;
    }
}
