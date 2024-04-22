package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alenum.PLRuleEventType;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PLRuleEvent extends BaseTable implements Serializable {

    @Column(name="name")
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PLRuleEventType plRuleEventType;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_rule_id", nullable = false)
    @JsonIgnore
    private PLRule puzzleLevelRule;

    @Column(name="event_Id")
    private Integer eventId;

    public PLRuleEvent() {
    }

    public PLRuleEvent(String name, PLRuleEventType plRuleEventType, Integer eventId, PLRule puzzleLevelRule, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.plRuleEventType = plRuleEventType;
        this.puzzleLevelRule = puzzleLevelRule;
        this.eventId = eventId;
    }

}
