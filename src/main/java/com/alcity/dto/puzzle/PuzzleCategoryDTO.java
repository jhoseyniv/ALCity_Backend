package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseItemSetDTO;

import java.util.Collection;

public class PuzzleCategoryDTO {
    private Long id;

    private String label;
    private String value;

    private Long version;
    private String created;
    private String updated;

    private String createdBy;
    private String updatedBy;

    private Long updatedById;
    private Long createdById;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
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

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public PuzzleCategoryDTO() {
    }

    public PuzzleCategoryDTO(Long id, String label, String value, Long version,
                             String created, String updated, String createdBy, String updatedBy, Long updatedById, Long getCreatedById) {
        this.id = id;
        this.label = label;
        this.value = value;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.updatedById = updatedById;
        this.createdById = createdById;
    }
}
