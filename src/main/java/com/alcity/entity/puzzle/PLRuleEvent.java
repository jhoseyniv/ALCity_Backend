package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.alenum.PLRuleEventType;
import com.alcity.entity.appmember.AppMember;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class PLRuleEvent extends BaseTable implements Serializable {


    @Column(name="name",unique = true)
    private String name;

    @Enumerated(EnumType.ORDINAL)
    private PLRuleEventType plRuleEventType;

    @Column(name="event_Id")
    private Integer eventId;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PLRuleEventType getPlRuleEventType() {
        return plRuleEventType;
    }

    public void setPlRuleEventType(PLRuleEventType plRuleEventType) {
        this.plRuleEventType = plRuleEventType;
    }

    public Integer getEventId() {
        return eventId;
    }

    public void setEventId(Integer eventId) {
        this.eventId = eventId;
    }

    public PLRuleEvent() {
    }

    public PLRuleEvent(String name, PLRuleEventType plRuleEventType, Integer eventId, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.name = name;
        this.plRuleEventType = plRuleEventType;
        this.eventId = eventId;
    }

}
