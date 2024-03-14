package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PuzzleGroup_PuzzleObject extends BaseTable implements Serializable {

    @Column(name="title")
    private String title;

    @Column(name="code")
    private String code;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_group_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_object_id", nullable = false)
    @JsonIgnore
    private PuzzleObject puzzleObject;

    @OneToMany(mappedBy = "puzzleGroup_PuzzleObject", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleGroupObjectInstance> puzzleGroupObjectInstanceSet;


    public PuzzleGroup_PuzzleObject() {
    }

    public PuzzleGroup_PuzzleObject( String title, String code, PuzzleGroup puzzleGroup, PuzzleObject puzzleObject,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.code = code;
        this.puzzleGroup = puzzleGroup;
        this.puzzleObject = puzzleObject;
    }
}
