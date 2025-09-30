package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Table(name = "pglearning_skill_content")

@Entity
@Getter
@Setter
@NoArgsConstructor

public class PGLearningSkill extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_skill_id", nullable = true)
    @JsonIgnore
    private LearningSkill learningSkill;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_group_id", nullable = true)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_content_id", nullable = true)
    @JsonIgnore
    private LearningContent learningContent;

    public PGLearningSkill(LearningSkill learningSkill, PuzzleGroup puzzleGroup, LearningContent learningContent, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.learningSkill = learningSkill;
        this.puzzleGroup = puzzleGroup;
        this.learningContent = learningContent;
    }
}
