package com.alcity.dto.pl;

import java.io.Serializable;

public class PLObjectiveData implements Serializable {
    private Long id;
    private String title;
    private String description;
    private Float skillAmount;
    private Long skillId;
    private Float rewardAmount;

    private Long rewardId;
    private StringBuffer condition;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getSkillAmount() {
        return skillAmount;
    }

    public void setSkillAmount(Float skillAmount) {
        this.skillAmount = skillAmount;
    }

    public Float getRewardAmount() {
        return rewardAmount;
    }

    public void setRewardAmount(Float rewardAmount) {
        this.rewardAmount = rewardAmount;
    }

    public StringBuffer getCondition() {
        return condition;
    }

    public void setCondition(StringBuffer condition) {
        this.condition = condition;
    }

    public Long getRewardId() {
        return rewardId;
    }

    public void setRewardId(Long rewardId) {
        this.rewardId = rewardId;
    }

    public PLObjectiveData() {
    }

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public PLObjectiveData(Long  id , String title, String description,Long skillId, Float skillAmount, Long rewardId, Float rewardAmount, StringBuffer condition) {
        this.title = title;
        this.description = description;
        this.skillId = skillId;
        this.skillAmount = skillAmount;
        this.rewardAmount = rewardAmount;
        this.rewardId = rewardId;
        this.condition = condition;
    }
}
