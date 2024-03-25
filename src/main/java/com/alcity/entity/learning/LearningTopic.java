package com.alcity.entity.learning;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class LearningTopic extends BaseTable {

    @NotNull(message = "{bHeight.notempty}")
    private String title;

    @ManyToOne(fetch = FetchType.LAZY)
    private LearningTopic parentTopic;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Set<LearningTopic> learningTopicSet;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LearningTopic getParentTopic() {
        return parentTopic;
    }

    public void setParentTopic(LearningTopic parentTopic) {
        this.parentTopic = parentTopic;
    }

    public Set<LearningTopic> getLearningTopicSet() {
        return learningTopicSet;
    }

    public void setLearningTopicSet(Set<LearningTopic> learningTopicSet) {
        this.learningTopicSet = learningTopicSet;
    }

    public LearningTopic() {
    }

    public LearningTopic(String title, LearningTopic parentTopic , Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.parentTopic = parentTopic;
    }
}
