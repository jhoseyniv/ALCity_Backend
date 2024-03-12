package com.alcity.entity.puzzle;

import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class PuzzleLevelLearningTopic extends RecordInformation {


    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "puzzle_Level_id", nullable = true)
    @JsonIgnore
    private PuzzleLevel puzzleLevel;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_topic_id", nullable = true)
    @JsonIgnore
    private LearningTopic learningTopic;

    @ManyToOne(fetch = FetchType.LAZY, optional = true)
    @JoinColumn(name = "learning_content_id", nullable = true)
    @JsonIgnore
    private LearningContent learningContent;

    public PuzzleLevelLearningTopic() {
    }

    public PuzzleLevelLearningTopic(PuzzleLevel puzzleLevel, LearningTopic learningTopic, LearningContent learningContent,Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.puzzleLevel = puzzleLevel;
        this.learningTopic = learningTopic;
        this.learningContent = learningContent;
    }
}
