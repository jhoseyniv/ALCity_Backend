package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alenum.PLRuleEventType;
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

public class PLRuleEvent extends BaseTable implements Serializable {


    @Column(name="name",unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PLRuleEventType plRuleEventType;

    @Column(name="event_Id")
    private Integer eventId;

    @OneToMany(mappedBy = "plRuleEvent", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PLRule> plRules;

    public PLRuleEvent(String name, PLRuleEventType plRuleEventType, Integer eventId, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.plRuleEventType = plRuleEventType;
        this.eventId = eventId;
    }

}
