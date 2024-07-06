package com.alcity.dto.base;


public class BinaryContentDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String fileName;
    private Integer size;
    private byte[] content;

    private String contentType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public BinaryContentDTO() {
    }

    public BinaryContentDTO(Long id,Long version, String created, String updated, String createdBy, String updatedBy, String fileName, Integer size, byte[] content,String contentType) {
        this.id = id;
        this.created = created;
        this.version = version;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.fileName = fileName;
        this.size = size;
        this.content = content;
        this.contentType = contentType;
    }
}
