package com.alcity.dto.puzzle.object;

public class ActionDTO {
    private Long id;
    private Long ownerObjectid;

    private String objectAction;
    private Long   objectActionId;

    private String actionRender;
    private Long actionRenderId;
    private String ownerType;

    public Long getOwnerObjectid() {
        return ownerObjectid;
    }

    public void setOwnerObjectid(Long ownerObjectid) {
        this.ownerObjectid = ownerObjectid;
    }



    public ActionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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


    public ActionDTO(Long id, Long ownerObjectid, String objectAction, Long objectActionId, String actionRender, Long actionRenderId, String ownerType) {
        this.id = id;
        this.ownerObjectid = ownerObjectid;
        this.objectAction = objectAction;
        this.objectActionId = objectActionId;
        this.actionRender = actionRender;
        this.actionRenderId = actionRenderId;
        this.ownerType = ownerType;
    }
}
