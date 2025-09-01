package com.alcity.entity.appmember;

import com.alcity.entity.base.BaseTable;
import com.alcity.entity.learning.LearningSkill;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

@Entity
public class LearningSkillTransaction   extends BaseTable implements Serializable {

    @NotNull(message = "{transactionDate.notempty}")
    private String transactionDate;

    @NotNull(message = "{amount.notempty}")
    private Float amount;

    @NotNull(message = "{description.notempty}")
    private String description;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "learning_skill_id", nullable = false)
    @JsonIgnore
    private LearningSkill learningSkill;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "applicationMember_id", nullable = false)
    @JsonIgnore
    private AppMember appMember;

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LearningSkill getLearningSkill() {
        return learningSkill;
    }

    public void setLearningSkill(LearningSkill learningSkill) {
        this.learningSkill = learningSkill;
    }

    public AppMember getAppMember() {
        return appMember;
    }

    public void setAppMember(AppMember appMember) {
        this.appMember = appMember;
    }


    public LearningSkillTransaction() {
    }

    public LearningSkillTransaction(Long version, String created, String updated, AppMember createdBy, AppMember updatedBy,
                                    String transactionDate, Float amount, String description, LearningSkill learningSkill, AppMember appMember) {
        super(version, created, updated, createdBy, updatedBy);
        this.transactionDate = transactionDate;
        this.amount = amount;
        this.description = description;
        this.learningSkill = learningSkill;
        this.appMember = appMember;
    }
}
