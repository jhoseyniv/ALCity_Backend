package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class LearningSkillContent extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_skill_id", nullable = true)
    @JsonIgnore
    private LearningSkill learningSkill;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_group_id", nullable = true)
    @JsonIgnore
    private PuzzleGroup puzzleGroup;

    public PuzzleGroup getPuzzleGroup() {
        return puzzleGroup;
    }

    public void setPuzzleGroup(PuzzleGroup puzzleGroup) {
        this.puzzleGroup = puzzleGroup;
    }

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_content_id", nullable = true)
    @JsonIgnore
    private LearningContent learningContent;

    public LearningSkill getLearningSkill() {
        return learningSkill;
    }

    public void setLearningSkill(LearningSkill learningSkill) {
        this.learningSkill = learningSkill;
    }

    public LearningContent getLearningContent() {
        return learningContent;
    }

    public void setLearningContent(LearningContent learningContent) {
        this.learningContent = learningContent;
    }

    public LearningSkillContent() {
    }

    public LearningSkillContent(LearningSkill learningSkill, PuzzleGroup puzzleGroup, LearningContent learningContent, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.learningSkill = learningSkill;
        this.puzzleGroup = puzzleGroup;
        this.learningContent = learningContent;
    }
}
