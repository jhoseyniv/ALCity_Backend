package com.alcity.entity.puzzle;


import com.alcity.entity.base.BinaryContent;
import com.alcity.entity.base.PuzzleCategory;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.journey.JourneyStep;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
public class PuzzleGroup extends BaseTable implements Serializable {

    @Column(name="title")
    private String title;


    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<JourneyStep> journeyStepSet;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_category_id", nullable = false)
    @JsonIgnore
    private PuzzleCategory puzzleCategory;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "icon_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent icon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pic_content_id", nullable = false)
    @JsonIgnore
    private BinaryContent pic;



    @OneToMany(mappedBy = "puzzleGroup", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<PuzzleLevel> puzzleLevelSet;

    public PuzzleGroup() {
    }

    public PuzzleGroup( String title,PuzzleCategory puzzleCategory, BinaryContent icon, BinaryContent pic,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.puzzleCategory = puzzleCategory;
        this.icon = icon;
        this.pic = pic;
    }
}
