package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.PLRulePostActionOwnerType;
import com.alcity.entity.alenum.UserGender;
import com.alcity.entity.alobject.PLRulePostActionType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alobject.RulePostActionEvent;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class PLRulePostAction extends BaseTable implements Serializable {

    @Column(name="valueExperssion")
    private StringBuffer valueExperssion;

    @Column(name="variable")
    private String variable;

    @Column(name="ordering")
    private Integer ordering;

    @Column(name="objectId")
    private String objectId;

    @Column(name="actionName")
    private String actionName;

    @Column(name="alertType")
    private String alertType;
    @Column(name="actionKey")
    private Long actionKey;

    @Column(name="alertMessage")
    private String alertMessage;

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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pl_rule_post_action_type_id", nullable = false)
    @JsonIgnore
    private PLRulePostActionType plRulePostActionType;

    @OneToMany(mappedBy = "plRulePostAction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<RulePostActionEvent> rulePostActionEventCollection;

    public Long getOwnerId() {
        return ownerId;
    }

    public void setOwnerId(Long ownerId) {
        this.ownerId = ownerId;
    }

    public PLRulePostActionOwnerType getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(PLRulePostActionOwnerType ownerType) {
        this.ownerType = ownerType;
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

    public Collection<RulePostActionEvent> getRulePostActionEventCollection() {
        return rulePostActionEventCollection;
    }

    public void setRulePostActionEventCollection(Collection<RulePostActionEvent> rulePostActionEventCollection) {
        this.rulePostActionEventCollection = rulePostActionEventCollection;
    }


    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public PLRulePostActionType getPlRulePostActionType() {
        return plRulePostActionType;
    }

    public void setPlRulePostActionType(PLRulePostActionType plRulePostActionType) {
        this.plRulePostActionType = plRulePostActionType;
    }

//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    @JoinColumn(name = "puzzle_Level_rule_id", nullable = false)
//    @JsonIgnore

    private Long ownerId;

    @Enumerated(EnumType.ORDINAL)
    private PLRulePostActionOwnerType ownerType;


    public PLRulePostAction() {
    }

    public Long getActionKey() {
        return actionKey;
    }

    public void setActionKey(Long actionKey) {
        this.actionKey = actionKey;
    }

    public PLRulePostAction(Long ownerId,PLRulePostActionOwnerType ownerType , PLRulePostActionType plRulePostActionType, Integer ordering, String actionName, String objectId,
                            String variable, StringBuffer valueExperssion, String alertType, String alertMessage , Long actionKey,
                            Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.valueExperssion = valueExperssion;
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
