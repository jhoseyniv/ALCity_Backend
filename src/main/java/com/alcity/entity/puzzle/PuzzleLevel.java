package com.alcity.entity.puzzle;


import com.alcity.entity.base.*;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class PuzzleLevel extends RecordInformation implements Serializable {

    @Column(name="approveDate")
    private Long approveDate;

    @Column(name="ordering")
    private Long ordering;

    @Column(name="title")
    private String title;

    @Column(name="code")
    private String code;


    @Column(name="fromAge")
    private Integer fromAge;

    @Column(name="toAge")
    private Integer toAge;

    @Column(name="maxScore")
    private Float maxScore;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_group_id", nullable = false)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_difficulty_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelDifficulty puzzleDifficulty;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_status_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelStatus puzzleLevelStatus;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "puzzle_level_privacy_id", nullable = false)
    @JsonIgnore
    private PuzzleLevelPrivacy puzzleLevelPrivacy;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "picture_id", nullable = true)
    @JsonIgnore
    private BinaryContent picture;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "icon_id", nullable = true)
    @JsonIgnore
    private BinaryContent icon;

    public PuzzleLevel() {
    }

    public PuzzleLevel( Long approveDate, Long ordering, String title, String code, Integer fromAge, Integer toAge, Float maxScore, PuzzleGroup puzzleGroup, PuzzleLevelDifficulty puzzleDifficulty, PuzzleLevelStatus puzzleLevelStatus,PuzzleLevelPrivacy puzzleLevelPrivacy,BinaryContent picture,BinaryContent icon,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.approveDate = approveDate;
        this.ordering = ordering;
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.maxScore = maxScore;
        this.puzzleGroup = puzzleGroup;
        this.puzzleDifficulty = puzzleDifficulty;
        this.puzzleLevelStatus = puzzleLevelStatus;
        this.puzzleLevelPrivacy = puzzleLevelPrivacy;
        this.picture = picture;
        this.icon = icon;
    }
}
