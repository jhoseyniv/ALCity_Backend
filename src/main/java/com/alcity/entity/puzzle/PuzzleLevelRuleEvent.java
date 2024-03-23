package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.PuzzleLevelRuleEventType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleLevelRuleEvent extends BaseTable implements Serializable {

    @Column(name="name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_rule_event_type_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelRuleEventType puzzleLevelRuleEventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_rule_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelRule puzzleLevelRule;

    @Column(name="event_Id")
    private Long eventId;

    public PuzzleLevelRuleEvent() {
    }

    public PuzzleLevelRuleEvent(String name, PuzzleLevelRuleEventType puzzleLevelRuleEventType, Long eventId, PuzzleLevelRule puzzleLevelRule,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.puzzleLevelRuleEventType = puzzleLevelRuleEventType;
        this.puzzleLevelRule = puzzleLevelRule;
        this.eventId = eventId;
    }

}
