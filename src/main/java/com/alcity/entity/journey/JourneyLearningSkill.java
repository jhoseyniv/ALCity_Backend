package com.alcity.entity.journey;

import com.alcity.entity.base.LearningSkill;
import com.alcity.entity.base.RecordInformation;
import com.alcity.entity.users.ApplicationMember;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class JourneyLearningSkill extends RecordInformation implements Serializable {

    @Column(name="requiredAmount")
    private Float requiredAmount;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "journey_id", nullable = false)
    @JsonIgnore
    private Journey journey;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_skill_id", nullable = false)
    @JsonIgnore
    private LearningSkill learningSkill;

    public JourneyLearningSkill() {
    }

    public JourneyLearningSkill(Float requiredAmount, Journey journey, LearningSkill learningSkill , Long version, Long created, Long updated, ApplicationMember createdBy, ApplicationMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.requiredAmount = requiredAmount;
        this.journey = journey;
        this.learningSkill = learningSkill;
    }
}
