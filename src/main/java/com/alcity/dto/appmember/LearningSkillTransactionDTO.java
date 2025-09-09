package com.alcity.dto.appmember;

import java.io.Serializable;

public class LearningSkillTransactionDTO implements Serializable {
    private Float amount;
    private Long appMemberId;
    private Long learningSkillId;
    private Long objectiveId;
    private Integer stars;
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

    public Long getObjectiveId() {
        return objectiveId;
    }

    public void setObjectiveId(Long objectiveId) {
        this.objectiveId = objectiveId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public LearningSkillTransactionDTO() {
    }

    public LearningSkillTransactionDTO(Float amount,Integer stars , Long appMemberId, Long learningSkillId,Long objectiveId,String description) {
        this.amount = amount;
        this.stars = stars;
        this.appMemberId = appMemberId;
        this.learningSkillId = learningSkillId;
        this.objectiveId = objectiveId;
        this.description = description;
    }
}
