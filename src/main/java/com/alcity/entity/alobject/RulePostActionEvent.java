package com.alcity.entity.alobject;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.puzzle.PLRulePostAction;
import com.alcity.entity.users.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class RulePostActionEvent extends BaseTable implements Serializable {

    public RulePostActionEvent() {
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pl_rule_post_action_id", nullable = false)
    @JsonIgnore
    private PLRulePostAction plRulePostAction;

    public RulePostActionEvent(PLRulePostAction plRulePostAction, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.plRulePostAction = plRulePostAction;
    }
}
