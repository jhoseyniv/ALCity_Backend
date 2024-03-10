package com.alcity.entity.journey;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class JourneyStep extends RecordInformation implements Serializable {


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

    public JourneyStep() {
    }

    public JourneyStep(String title,Integer ordering, Integer xpos, Integer ypos, Journey journey, PuzzleGroup puzzleGroup ,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title =title;
        this.ordering = ordering;
        this.xpos = xpos;
        this.ypos = ypos;
        this.journey = journey;
        this.puzzleGroup = puzzleGroup;
    }
}
