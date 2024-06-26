package com.alcity.dto.alobject;

public class RendererDTO {

    private Long id;
    private String objectAction;
    private String handler;
    private String clientType;

    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;


    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
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

    public RendererDTO() {
    }

    public String getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(String objectAction) {
        this.objectAction = objectAction;
    }

    public String getClientType() {
        return clientType;
    }

    public void setClientType(String clientType) {
        this.clientType = clientType;
    }

    public RendererDTO(String objectAction, String handler, String clientType, Long id, Long version, String created, String updated, String createdBy, String updatedBy) {
        this.objectAction = objectAction;
        this.handler = handler;
        this.clientType = clientType;
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
