package com.alcity.entity.learning;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.*;
import java.io.Serializable;

@Entity
public class LearningSkillTopic extends BaseTable implements Serializable {


    @Column(name="title",unique = true)
    private String title;


    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_skill_id", referencedColumnName = "id")
    @JsonProperty("learningSkill")
    private LearningSkill learningSkill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_topic_id", referencedColumnName = "id")
    @JsonIgnore
    private LearningTopic learningTopic;


    public LearningSkillTopic() {
    }

    public LearningSkillTopic(String title, LearningSkill learningSkill, LearningTopic learningTopic, Long version, String created, String updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.learningSkill = learningSkill;
        this.learningTopic = learningTopic;
    }
}
