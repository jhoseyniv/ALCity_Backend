package com.alcity.dto.Interpreter.object;

import java.io.Serializable;
import java.util.Collection;

public class RuleActionData implements Serializable {
    private String actionType;
    private Integer ordering;
    private String objectId;
    private String actionName;

    private String variable;
    private StringBuffer valueExperssion;
    private String alertType;
    private String alertMessage;
    private Long actionKey;

    private Collection<RecordData> parameters;

    public String getActionType() {
        return actionType;
    }

    public void setActionType(String actionType) {
        this.actionType = actionType;
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

    public Collection<RecordData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<RecordData> parameters) {
        this.parameters = parameters;
    }

    public Long getActionKey() {
        return actionKey;
    }

    public void setActionKey(Long actionKey) {
        this.actionKey = actionKey;
    }

    public RuleActionData() {
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
    }

    public StringBuffer getValueExperssion() {
        return valueExperssion;
    }

    public void setValueExperssion(StringBuffer valueExperssion) {
        this.valueExperssion = valueExperssion;
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


    public RuleActionData(String actionType, Integer ordering, String objectId, String actionName, String variable, StringBuffer valueExperssion ,String alertType, String alertMessage, Long actionKey, Collection<RecordData> parameters) {
        this.actionType = actionType;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.variable = variable;
        this.valueExperssion = valueExperssion;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.actionKey = actionKey;
        this.parameters = parameters;
    }
}
