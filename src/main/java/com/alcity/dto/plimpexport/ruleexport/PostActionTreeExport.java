package com.alcity.dto.plimpexport.ruleexport;

import com.alcity.dto.plimpexport.AttributeData;
import com.alcity.entity.alenum.PLRulePostActionOwnerType;
import com.alcity.entity.puzzle.PLRulePostAction;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PostActionTreeExport<P> implements Serializable {

    private String actionType;
    private Integer ordering;
    private String objectId;
    private String actionName;

  //  private String subAction;

    private String variable;
    private StringBuffer valueExperssion;
    private String alertType;
    private String alertMessage;
    private String actionKey;

    private Collection<AttributeData> parameters;

    public List<PostActionTreeExport<PostActionTreeExport>> innerActions;
    public List<PostActionTreeExport<PostActionTreeExport>> elseActions;

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

    public Collection<AttributeData> getParameters() {
        return parameters;
    }

    public void setParameters(Collection<AttributeData> parameters) {
        this.parameters = parameters;
    }

    public String getActionKey() {
        return actionKey;
    }

    public void setActionKey(String actionKey) {
        this.actionKey = actionKey;
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


    public List<PostActionTreeExport<PostActionTreeExport>> getInnerActions() {
        return innerActions;
    }

    public void setInnerActions(List<PostActionTreeExport<PostActionTreeExport>> innerActions) {
        this.innerActions = innerActions;
    }

    public List<PostActionTreeExport<PostActionTreeExport>> getElseActions() {
        return elseActions;
    }

    public void setElseActions(List<PostActionTreeExport<PostActionTreeExport>> elseActions) {
        this.elseActions = elseActions;
    }

//    public String getSubAction() {
//        return subAction;
//    }
//
//    public void setSubAction(String subAction) {
//        this.subAction = subAction;
//    }

    public PostActionTreeExport() {
        this.innerActions = new ArrayList<>();
    }

    public PostActionTreeExport(String actionType,Integer ordering ,String objectId,String actionName,String variable,
                                StringBuffer valueExperssion,String alertType,String alertMessage,String actionKey,
                                List<PostActionTreeExport<PostActionTreeExport>> innerActions,List<PostActionTreeExport<PostActionTreeExport>> elseActions) {
        this.actionType = actionType;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.variable = variable;
        this.valueExperssion = valueExperssion;
     //   this.subAction =subAction;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.actionKey = actionKey;
        this.elseActions = elseActions;
        this.innerActions = innerActions;
    }
    public void setFiedlds(String actionType,Integer ordering ,String objectId,String actionName,String variable,
                                StringBuffer valueExperssion,String alertType,String alertMessage,String actionKey,Collection<AttributeData> parameters) {
        this.actionType = actionType;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.variable = variable;
        this.valueExperssion = valueExperssion;
       // this.subAction =subAction;
        this.alertType = alertType;
        this.alertMessage = alertMessage;
        this.actionKey = actionKey;
        this.parameters =parameters;
        this.innerActions = new ArrayList<>();
        this.elseActions = new ArrayList<>();
    }
    // Optional: add a method to add a child
    public void addChild(PostActionTreeExport<PostActionTreeExport> child) {
        innerActions.add(child);
        elseActions.add(child);
    }
    public PostActionTreeExport<PostActionTreeExport> getChild(PostActionTreeExport<PostActionTreeExport> child, PLRulePostActionOwnerType ownerType) {
        if(ownerType.equals(PLRulePostActionOwnerType.Inner_Rule_Post_Action))
            innerActions.add(child);
        if(ownerType.equals(PLRulePostActionOwnerType.Else))
            elseActions.add(child);
        return child;
    }

    // Optional: enforce a maximum of N children
    public void addChild(PostActionTreeExport<PostActionTreeExport> child, int maxChildren) {
        if (innerActions.size() < maxChildren) {
            innerActions.add(child);
        } else {
            throw new IllegalStateException("Maximum number of children (" + maxChildren + ") exceeded");
        }
        if (elseActions.size() < maxChildren) {
            elseActions.add(child);
        } else {
            throw new IllegalStateException("Maximum number of children (" + maxChildren + ") exceeded");
        }
    }


}
