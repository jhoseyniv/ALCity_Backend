package com.alcity.test.ruleimport_new;


import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.dto.plimpexport.ruleexport.PostActionTreeExport;
import com.alcity.dto.plimpexport.rulemport.PLRulePostActionImport;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostActionTreeImport_New<P> implements Serializable {

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

    public List<PostActionTreeImport_New<PostActionTreeImport_New>> innerActions;
    public List<PostActionTreeImport_New<PostActionTreeImport_New>> elseActions;


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

    public PostActionTreeImport_New() {
    }


}


