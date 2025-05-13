package com.alcity.dto.puzzle;

import com.alcity.entity.alobject.Attribute;

import java.util.Collection;

public class PLRulePostActionDTO {
    private Long id;

    private Long ownerId;

    private String ownerType;

    private String subAction;
    private StringBuffer valueExperssion;
    private String variable;
    private Integer ordering;
    private String objectId;
    private String actionName;
    private String alertType;
    private String alertMessage;

    private Long actionKey;
    private String plRulePostActionType;

    private Collection<Attribute> parameters ;

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

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public Long getActionKey() {
        return actionKey;
    }

    public void setActionKey(Long actionKey) {
        this.actionKey = actionKey;
    }

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public Collection<Attribute> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<Attribute> parameters) {
        this.parameters = parameters;
    }

    public PLRulePostActionDTO() {
    }


    public PLRulePostActionDTO(Long id, StringBuffer valueExperssion,String subAction, String variable, Integer ordering, String objectId,
                               String actionName, String alertType, String alertMessage,Long actionKey, String plRulePostActionType,Long ownerId,String ownerType) {
        this.id = id;
        this.valueExperssion = valueExperssion;
        this.subAction = subAction;
        this.variable = variable;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.actionKey = actionKey;
        this.plRulePostActionType = plRulePostActionType;
        this.ownerId = ownerId;
        this.ownerType = ownerType;
    }
}
