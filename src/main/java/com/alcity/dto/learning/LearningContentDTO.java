package com.alcity.dto.learning;

public class LearningContentDTO  {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;


    private String descText;
    private String descBrief;
    private String fileName;
    private Integer size;
    private byte[] content;

    private String contentType;

    private Long binaryContentId;



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

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public Long getBinaryContentId() {
        return binaryContentId;
    }

    public void setBinaryContentId(Long binaryContentId) {
        this.binaryContentId = binaryContentId;
    }

    public LearningContentDTO() {
    }

    public LearningContentDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, String descText, String descBrief, String fileName, Integer size, String contentType, Long binaryContentId) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.descText = descText;
        this.descBrief = descBrief;
        this.fileName = fileName;
        this.size = size;
        this.contentType = contentType;
        this.binaryContentId = binaryContentId;
    }
}
