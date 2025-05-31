package com.alcity.entity.appmember;


import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.io.Serializable;

@Entity
public class AppMember_LearningSkill extends BaseTable implements Serializable {

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "application_member_id", referencedColumnName = "id")
    @JsonIgnore
    private AppMember applicationMember;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_skill_id", referencedColumnName = "id")
    @JsonIgnore
    private LearningSkill learningSkill;

    @Column(name="amount")
    private Float amount;

    public AppMember getApplicationMember() {
        return applicationMember;
    }

    public void setApplicationMember(AppMember applicationMember) {
        this.applicationMember = applicationMember;
    }

    public LearningSkill getLearningSkill() {
        return learningSkill;
    }

    public void setLearningSkill(LearningSkill learningSkill) {
        this.learningSkill = learningSkill;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public AppMember_LearningSkill() {
    }

    public AppMember_LearningSkill(AppMember applicationMember, LearningSkill learningSkill, Float amount) {
        this.applicationMember = applicationMember;
        this.learningSkill = learningSkill;
        this.amount = amount;
    }

}
