package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
public class PLRule extends BaseTable implements Serializable {
    @Column(name="title")
    private String title;

    @Column(name="ordering")
    private Integer ordering;

    @Column(name="condition")
    private StringBuffer condition;

    //why this column is in puzzle level rule
//    @Column(name="ruleEventid")
//    private Integer ruleEventid;

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

    @OneToMany(mappedBy = "puzzleLevelRule", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Collection<PLRulePostAction> plRulePostActions;

    public Collection<PLRulePostAction> getPlRulePostActions() {
        return plRulePostActions;
    }

    public void setPlRulePostActions(Collection<PLRulePostAction> plRulePostActions) {
        this.plRulePostActions = plRulePostActions;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

     public PLRule() {
    }

    public PLRule(String title, Integer ordering, StringBuffer condition, PuzzleLevel puzzleLevel, Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.ordering = ordering;
        this.condition = condition;
       // this.ruleEventid = ruleEventid;
        this.puzzleLevel = puzzleLevel;
    }
}