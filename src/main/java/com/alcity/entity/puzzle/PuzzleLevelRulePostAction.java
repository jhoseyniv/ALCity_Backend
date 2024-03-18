package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.PuzzleLevelRulePostActionType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleLevelRulePostAction extends BaseTable implements Serializable {

    @Column(name="actionExpression")
    private String actionExpression;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "action_type_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelRulePostActionType actionTypeid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rule_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelRule ruleId;

    public PuzzleLevelRulePostAction() {
    }

    public PuzzleLevelRulePostAction(String actionExpression, PuzzleLevelRulePostActionType actionTypeid, PuzzleLevelRule ruleId, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.actionExpression = actionExpression;
        this.actionTypeid = actionTypeid;
        this.ruleId = ruleId;
    }
}
