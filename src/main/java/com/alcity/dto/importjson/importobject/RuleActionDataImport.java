package com.alcity.dto.Interpreter.object;

import java.io.Serializable;
import java.util.Collection;

public class RuleActionDataImport implements Serializable {
    private String actionType;
    private Integer ordering;
    private String objectId;
    private String actionName;

    private String variable;
    private StringBuffer valueExperssion;
    private String alertType;
    private String alertMessage;

    private Collection<RecordDataImport> parameters;

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

    public Collection<RecordDataImport> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<RecordDataImport> parameters) {
        this.parameters = parameters;
    }

    public RuleActionDataImport() {
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

    public RuleActionDataImport(String actionType, Integer ordering, String objectId, String actionName, String variable, StringBuffer valueExperssion, String alertType, String alertMessage, Collection<RecordDataImport> parameters) {
        this.actionType = actionType;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.variable = variable;
        this.valueExperssion = valueExperssion;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.parameters = parameters;
    }
}
