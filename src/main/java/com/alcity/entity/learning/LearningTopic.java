package com.alcity.entity.learning;

import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
public class LearningTopic extends RecordInformation {

    @NotNull(message = "{bHeight.notempty}")
    private String title;

//    @Column(name="parentId", insertable=false, updatable=false)
//    private Long parentId;

    @ManyToOne(fetch = FetchType.LAZY)
    private LearningTopic parentTopic;

    @OneToMany( fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<LearningTopic> learningTopicSet;

    public LearningTopic() {
    }

    public LearningTopic(String title, LearningTopic parentTopic , Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.title = title;
        this.parentTopic = parentTopic;
    }
}
