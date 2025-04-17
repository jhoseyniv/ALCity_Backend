package com.alcity.dto.puzzle;

import com.alcity.entity.alobject.PLRulePostActionType;

public class PLRulePostActionDTO {
    private Long id;

    private Long puzzleLevelRuleId;
    private StringBuffer valueExperssion;
    private String variable;
    private Integer ordering;
    private String objectId;
    private String actionName;
    private String alertType;
    private String alertMessage;
    private String plRulePostActionType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public StringBuffer getValueExperssion() {
        return valueExperssion;
    }

    public void setValueExperssion(StringBuffer valueExperssion) {
        this.valueExperssion = valueExperssion;
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getAlertMessage() {
        return alertMessage;
    }

    public void setAlertMessage(String alertMessage) {
        this.alertMessage = alertMessage;
    }

    public String getPlRulePostActionType() {
        return plRulePostActionType;
    }

    public void setPlRulePostActionType(String plRulePostActionType) {
        this.plRulePostActionType = plRulePostActionType;
    }

    public PLRulePostActionDTO() {
    }

    public Long getPuzzleLevelRuleId() {
        return puzzleLevelRuleId;
    }

    public void setPuzzleLevelRuleId(Long puzzleLevelRuleId) {
        this.puzzleLevelRuleId = puzzleLevelRuleId;
    }

    public PLRulePostActionDTO(Long id, StringBuffer valueExperssion, String variable, Integer ordering, String objectId,
                               String actionName, String alertType, String alertMessage, String plRulePostActionType,Long puzzleLevelRuleId) {
        this.id = id;
        this.valueExperssion = valueExperssion;
        this.variable = variable;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.plRulePostActionType = plRulePostActionType;
        this.puzzleLevelRuleId = puzzleLevelRuleId;
    }
}
