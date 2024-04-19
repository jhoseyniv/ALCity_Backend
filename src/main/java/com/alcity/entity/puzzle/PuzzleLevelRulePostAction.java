package com.alcity.entity.puzzle;

import com.alcity.entity.alenum.PLRulePostActionType;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleLevelRulePostAction extends BaseTable implements Serializable {

    @Column(name="actionExpression")
    private StringBuffer actionExpression;

    @Column(name="ordering")
    private Integer ordering;


    @Enumerated(EnumType.ORDINAL)
    private PLRulePostActionType plRulePostActionType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "rule_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelRule ruleId;

    public PuzzleLevelRulePostAction() {
    }

    public PuzzleLevelRulePostAction(PuzzleLevelRule ruleId, Integer ordering, StringBuffer actionExpression, PLRulePostActionType plRulePostActionType, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.ordering = ordering;
        this.actionExpression = actionExpression;
        this.plRulePostActionType = plRulePostActionType;
        this.ruleId = ruleId;
    }
}
