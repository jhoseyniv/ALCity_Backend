package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class PLRule extends BaseTable implements Serializable {
    @Column(name="title")
    private String title;

    @Column(name="ordering")
    private Integer ordering;
    @Column(name="ignoreRemaining")
    private Boolean ignoreRemaining;


    @Column(name="condition")
    private StringBuffer condition;

    //why this column is in puzzle level rule
//    @Column(name="ruleEventid")
//    private Integer ruleEventid;

    @Column(name="subEvent" )
    private String subEvent;


    public String getSubEvent() {
        return subEvent;
    }

    public void setSubEvent(String subEvent) {
        this.subEvent = subEvent;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getOrdering() {
        return ordering;
    }

    public void setOrdering(Integer ordering) {
        this.ordering = ordering;
    }

    public StringBuffer getCondition() {
        return condition;
    }

    public void setCondition(StringBuffer condition) {
        this.condition = condition;
    }

    public Boolean getIgnoreRemaining() {
        return ignoreRemaining;
    }

    public void setIgnoreRemaining(Boolean ignoreRemaining) {
        this.ignoreRemaining = ignoreRemaining;
    }


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PL_rule_event_id", nullable = false)
    @JsonIgnore
    private PLRuleEvent plRuleEvent;

    public PLRuleEvent getPlRuleEvent() {
        return plRuleEvent;
    }

    public void setPlRuleEvent(PLRuleEvent plRuleEvent) {
        this.plRuleEvent = plRuleEvent;
    }

    public PuzzleLevel getPuzzleLevel() {
        return puzzleLevel;
    }

    public void setPuzzleLevel(PuzzleLevel puzzleLevel) {
        this.puzzleLevel = puzzleLevel;
    }

    public PLRule() {
    }

    public PLRule(String title ,Integer ordering, StringBuffer condition,boolean ignoreRemaining,  PuzzleLevel puzzleLevel, PLRuleEvent plRuleEvent,String subEvent , Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.subEvent = subEvent;
        this.ordering = ordering;
        this.condition = condition;
        this.ignoreRemaining = ignoreRemaining;
        this.plRuleEvent = plRuleEvent;
        this.puzzleLevel = puzzleLevel;
    }
}
