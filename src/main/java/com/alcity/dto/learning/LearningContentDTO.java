package com.alcity.dto.learning;

import com.alcity.dto.base.BinaryContentDTO;

public class LearningContentDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;


    private String descText;
    private String descBrief;
    private BinaryContentDTO binaryContentDTO;

    public LearningContentDTO() {
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

    public String getDescText() {
        return descText;
    }

    public void setDescText(String descText) {
        this.descText = descText;
    }

    public String getDescBrief() {
        return descBrief;
    }

    public void setDescBrief(String descBrief) {
        this.descBrief = descBrief;
    }

    public BinaryContentDTO getBinaryContentDTO() {
        return binaryContentDTO;
    }

    public void setBinaryContentDTO(BinaryContentDTO binaryContentDTO) {
        this.binaryContentDTO = binaryContentDTO;
    }

    public LearningContentDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String descText, String descBrief, BinaryContentDTO binaryContentDTO) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.descText = descText;
        this.descBrief = descBrief;
        this.binaryContentDTO = binaryContentDTO;
    }
}
