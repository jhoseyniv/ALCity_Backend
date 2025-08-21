package com.alcity.dto.plimpexport.rulemport;


import com.alcity.dto.plimpexport.AttributeData;

import java.io.Serializable;
import java.util.Collection;

public class PLRulePostActionImport implements Serializable {
    private String  postActionType;
    private String  postActionOwnerType;
    private Integer ordering;
    private String objectId;
    private String actionName;
    private String subAction;

    private String actionKey;

    private String variable;
    private StringBuffer valueExpression;
    private String alertType;
    private String alertMessage;
    private Collection<AttributeData> parameters;

    public String getPostActionType() {
        return postActionType;
    }

    public void setPostActionType(String postActionType) {
        this.postActionType = postActionType;
    }

    public StringBuffer getValueExpression() {
        return valueExpression;
    }

    public void setValueExpression(StringBuffer valueExpression) {
        this.valueExpression = valueExpression;
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

    public Collection<AttributeData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<AttributeData> parameters) {
        this.parameters = parameters;
    }

    public PLRulePostActionImport() {
    }

    public String getVariable() {
        return variable;
    }

    public void setVariable(String variable) {
        this.variable = variable;
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

    public String getPostActionOwnerType() {
        return postActionOwnerType;
    }

    public void setPostActionOwnerType(String postActionOwnerType) {
        this.postActionOwnerType = postActionOwnerType;
    }

    public String getSubAction() {
        return subAction;
    }

    public void setSubAction(String subAction) {
        this.subAction = subAction;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
    }

    public PLRulePostActionImport(String postActionType, String postActionOwnerType, Integer ordering, String objectId, String actionName,String subAction,String actionKey,
                                  String variable, StringBuffer valueExpression, String alertType, String alertMessage) {
        this.postActionOwnerType = postActionOwnerType;
        this.postActionType = postActionType;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.subAction = subAction;
        this.actionKey = actionKey;
        this.variable = variable;
        this.valueExpression = valueExpression;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
    }
}
