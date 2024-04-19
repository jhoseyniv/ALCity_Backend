package com.alcity.dto.Interpreter.object;

import java.util.Collection;

public class RuleActionData {
    private String actionType;
    private Integer ordering;
    private String objectId;
    private String actionName;
    private Collection<RecordrData> parameters;

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

    public Collection<RecordrData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<RecordrData> parameters) {
        this.parameters = parameters;
    }

    public RuleActionData() {
    }

    public RuleActionData(String actionType, Integer ordering, String objectId, String actionName, Collection<RecordrData> parameters) {
        this.actionType = actionType;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.parameters = parameters;
    }
}
