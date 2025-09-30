package com.alcity.entity.journey;

import com.alcity.entity.puzzle.PuzzleGroup;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Table( name ="JourneyStep" , uniqueConstraints={
        @UniqueConstraint(columnNames = {"title", "ordering","journey_id","puzzle_group_id"})
})
@Entity
@Getter
@Setter
@NoArgsConstructor

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
