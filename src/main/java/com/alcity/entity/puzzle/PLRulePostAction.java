package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.GameStatus;
import com.alcity.entity.alenum.PLRulePostActionOwnerType;
import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alobject.RulePostActionEvent;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Collection;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class PLRulePostAction extends BaseTable implements Serializable {


    @Column(name="subAction" )
    private String subAction;

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
    private String actionKey;

    @Column(name="alertMessage")
    private String alertMessage;


    @Enumerated(EnumType.ORDINAL)
    private PLRulePostActionType plRulePostActionType;

    @OneToMany(mappedBy = "plRulePostAction", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<RulePostActionEvent> rulePostActionEventCollection;

    private Long ownerId;

    @Enumerated(EnumType.ORDINAL)
    private PLRulePostActionOwnerType ownerType;

    public PLRulePostAction(Long ownerId,PLRulePostActionOwnerType ownerType , PLRulePostActionType plRulePostActionType, Integer ordering, String actionName, String objectId,
                            String variable, StringBuffer valueExperssion,String subAction, String alertType, String alertMessage , String actionKey,
                            Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
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
