package com.alcity.dto.alobject;

import com.alcity.dto.base.ClientTypeDTO;
import com.alcity.entity.alobject.ObjectAction;

public class ActionRendererDTO {

    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private String handler;
    private ClientTypeDTO clientTypeDTO;
    private ObjectAction objectAction;

    public String getHandler() {
        return handler;
    }

    public void setHandler(String handler) {
        this.handler = handler;
    }

    public ClientTypeDTO getClientTypeDTO() {
        return clientTypeDTO;
    }

    public void setClientTypeDTO(ClientTypeDTO clientTypeDTO) {
        this.clientTypeDTO = clientTypeDTO;
    }

    public ObjectAction getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(ObjectAction objectAction) {
        this.objectAction = objectAction;
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

    public ActionRendererDTO() {
    }

    public ActionRendererDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy,
                             String handler, ClientTypeDTO clientTypeDTO, ObjectAction objectAction) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.handler = handler;
        this.clientTypeDTO = clientTypeDTO;
        this.objectAction = objectAction;
    }
}
