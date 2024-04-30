package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alobject.RulePostActionEvent;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class PLRulePostAction extends BaseTable implements Serializable {

    @Column(name="actionExpression")
    private StringBuffer actionExpression;

    @Column(name="ordering")
    private Integer ordering;

    @Column(name="objectId")
    private String objectId;

    @Column(name="actionName")
    private String actionName;

    @Enumerated(EnumType.ORDINAL)
    private PLRulePostActionType plRulePostActionType;

    @OneToMany(mappedBy = "plRulePostAction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<RulePostActionEvent> rulePostActionEventCollection;

    public PLRule getPuzzleLevelRule() {
        return puzzleLevelRule;
    }

    public void setPuzzleLevelRule(PLRule puzzleLevelRule) {
        this.puzzleLevelRule = puzzleLevelRule;
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

    public StringBuffer getActionExpression() {
        return actionExpression;
    }

    public void setActionExpression(StringBuffer actionExpression) {
        this.actionExpression = actionExpression;
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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_Level_rule_id", nullable = false)
    @JsonIgnore
    private PLRule puzzleLevelRule;

    public PLRulePostAction() {
    }

    public PLRulePostAction(StringBuffer actionExpression, Integer ordering, String objectId, String actionName, PLRulePostActionType plRulePostActionType, PLRule puzzleLevelRule, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.actionExpression = actionExpression;
        this.ordering = ordering;
        this.objectId = objectId;
        this.actionName = actionName;
        this.plRulePostActionType = plRulePostActionType;
        this.puzzleLevelRule = puzzleLevelRule;
    }
}
