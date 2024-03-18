package com.alcity.entity.ruleengine;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.puzzle.PuzzleLevelRulePostAction;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.io.Serializable;

@Entity
public class RulePostActionEvent extends BaseTable implements Serializable {

    public RulePostActionEvent() {
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_rule_post_action_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelRulePostAction puzzleLevelRulePostAction;

    public RulePostActionEvent(PuzzleLevelRulePostAction puzzleLevelRulePostAction,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.puzzleLevelRulePostAction = puzzleLevelRulePostAction;
    }
}
