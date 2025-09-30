package com.alcity.entity.journey;

import com.alcity.entity.learning.LearningSkill;
import com.alcity.entity.base.BaseTable;
import com.alcity.entity.appmember.AppMember;
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

public class JourneyLearningSkill extends BaseTable implements Serializable {

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

    public JourneyLearningSkill(Float requiredAmount, Journey journey, LearningSkill learningSkill , Long version, String created, String updated, AppMember createdBy, AppMember updatedBy) {
        super(version, created, updated, createdBy, updatedBy);
        this.requiredAmount = requiredAmount;
        this.journey = journey;
        this.learningSkill = learningSkill;
    }
}
