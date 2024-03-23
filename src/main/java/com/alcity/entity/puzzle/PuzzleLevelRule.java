package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.journey.Journey;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PuzzleLevelRule extends BaseTable implements Serializable {
    @Column(name="title")
    private String title;

    @Column(name="ordering")
    private Integer ordering;

    @Column(name="condition")
    private String condition;

    //why this column is in puzzle level rule
//    @Column(name="ruleEventid")
//    private Integer ruleEventid;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_id", nullable = false)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

     public PuzzleLevelRule() {
    }

    public PuzzleLevelRule( String title, Integer ordering, String condition, PuzzleLevel puzzleLevel,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.ordering = ordering;
        this.condition = condition;
       // this.ruleEventid = ruleEventid;
        this.puzzleLevel = puzzleLevel;
    }
}
