package com.alcity.entity.puzzle;

import com.alcity.entity.appmember.AppMember;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.base.PuzzleCategory;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Getter
@Setter
@NoArgsConstructor

public class PLTemplate extends BaseTable implements Serializable {

    @Column(name="title" ,unique = true)
    private String title;

    @Column(name="fromAge" ,unique = false)
    private Integer fromAge;

    @Column(name="toAge" ,unique = false)
    private Integer toAge;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_category_id", nullable = true)
    @JsonIgnore
    private PuzzleCategory puzzleCategory;


    @Column(name="content")
    private StringBuffer content;


    @Column(name="puzzle_Level_Id")
    private Long puzzleLevelId;

    @Column(name="puzzle_group_Id")
    private Long puzzleGroupId;

    public PLTemplate(String title,Integer fromAge,Integer toAge, PuzzleCategory puzzleCategory,Long puzzleGroupId , Long puzzleLevelId, StringBuffer content ,Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.fromAge =fromAge;
        this.toAge = toAge;
        this.puzzleCategory = puzzleCategory;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleLevelId = puzzleLevelId;
        this.content = content;
    }
}
