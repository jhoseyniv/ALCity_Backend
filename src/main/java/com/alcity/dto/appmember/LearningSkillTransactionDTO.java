package com.alcity.dto.appmember;

import java.io.Serializable;

public class LearningSkillTransactionDTO implements Serializable {
    private Float amount;
    private Long appMemberId;
    private Long learningSkillId;

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

    public LearningSkillTransactionDTO() {
    }

    public LearningSkillTransactionDTO(Float amount, Long appMemberId, Long learningSkillId) {
        this.amount = amount;
        this.appMemberId = appMemberId;
        this.learningSkillId = learningSkillId;
    }
}
