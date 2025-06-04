package com.alcity.dto.pgimport;

public class PGObjectActionImportDTO {
    private  Long actionId;
    private String actionName;

    public Long getActionId() {
        return actionId;
    }

    public void setActionId(Long actionId) {
        this.actionId = actionId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public PGObjectActionImportDTO() {
    }

    public PGObjectActionImportDTO(Long actionId, String actionName) {
        this.actionId = actionId;
        this.actionName = actionName;
    }
}
