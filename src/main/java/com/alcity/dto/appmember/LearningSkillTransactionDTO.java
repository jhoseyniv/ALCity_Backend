package com.alcity.dto.appmember;

import java.io.Serializable;

public class LearningSkillTransactionDTO implements Serializable {
    private Float amount;
    private Long appMemberId;
    private Long learningSkillId;

    private  String description;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public Long getLearningSkillId() {
        return learningSkillId;
    }

    public void setLearningSkillId(Long learningSkillId) {
        this.learningSkillId = learningSkillId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LearningSkillTransactionDTO() {
    }

    public LearningSkillTransactionDTO(Float amount, Long appMemberId, Long learningSkillId,String description) {
        this.amount = amount;
        this.appMemberId = appMemberId;
        this.learningSkillId = learningSkillId;
        this.description = description;
    }
}
