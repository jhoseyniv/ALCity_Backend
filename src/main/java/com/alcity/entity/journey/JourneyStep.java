package com.alcity.entity.journey;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class JourneyStep extends BaseTable implements Serializable {

    @Column(name="title")
    private String title;

    @Column(name="ordering")
    private Integer ordering;

    @Column(name="xpos")
    private Integer xpos;

    @Column(name="ypos")
    private Integer ypos;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journey_id", nullable = false)
    @JsonIgnore
    private Journey journey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_group_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

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

    public Integer getXpos() {
        return xpos;
    }

    public void setXpos(Integer xpos) {
        this.xpos = xpos;
    }

    public Integer getYpos() {
        return ypos;
    }

    public void setYpos(Integer ypos) {
        this.ypos = ypos;
    }

    public Journey getJourney() {
        return journey;
    }

    public void setJourney(Journey journey) {
        this.journey = journey;
    }

    public PuzzleGroup getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(PuzzleGroup puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    public JourneyStep() {
    }

    public JourneyStep(String title, Integer ordering, Integer xpos, Integer ypos, Journey journey, PuzzleGroup puzzleGroup , Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title =title;
        this.ordering = ordering;
        this.xpos = xpos;
        this.ypos = ypos;
        this.journey = journey;
        this.puzzleGroup = puzzleGroup;
    }
}
