package com.alcity.dto.puzzle;

import com.alcity.dto.alobject.ActionRendererDTO;
import com.alcity.dto.alobject.PuzzleObjectActionOwnerTypeDTO;
import com.alcity.entity.alenum.ObjectAction;

public class PuzzleObjectActionDTO {
    private Long id;
    private Long ownerObjectid;

    private String objectAction;
    private Long   objectActionId;

    private String actionRender;
    private Long actionRenderId;
    private String ownerType;
    private Long ownerTypeId;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    public Long getOwnerObjectid() {
        return ownerObjectid;
    }

    public void setOwnerObjectid(Long ownerObjectid) {
        this.ownerObjectid = ownerObjectid;
    }



    public PuzzleObjectActionDTO() {
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

    public String getObjectAction() {
        return objectAction;
    }

    public void setObjectAction(String objectAction) {
        this.objectAction = objectAction;
    }

    public Long getObjectActionId() {
        return objectActionId;
    }

    public void setObjectActionId(Long objectActionId) {
        this.objectActionId = objectActionId;
    }

    public String getActionRender() {
        return actionRender;
    }

    public void setActionRender(String actionRender) {
        this.actionRender = actionRender;
    }

    public Long getActionRenderId() {
        return actionRenderId;
    }

    public void setActionRenderId(Long actionRenderId) {
        this.actionRenderId = actionRenderId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Long getOwnerTypeId() {
        return ownerTypeId;
    }

    public void setOwnerTypeId(Long ownerTypeId) {
        this.ownerTypeId = ownerTypeId;
    }
}
