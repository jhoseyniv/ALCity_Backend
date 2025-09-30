package com.alcity.entity.puzzle;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.appmember.AppMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Table(name = "learning_topic_inpl")
@Entity
@Getter
@Setter
@NoArgsConstructor

public class PLLearningTopic extends BaseTable {


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


    public PLLearningTopic(PuzzleLevel puzzleLevel, LearningTopic learningTopic, LearningContent learningContent, Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.puzzleLevel = puzzleLevel;
        this.learningTopic = learningTopic;
        this.learningContent = learningContent;
    }
}
