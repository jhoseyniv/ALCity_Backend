package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;

public class PLObjectiveDTO extends BaseTableDTO {

    private String title;
    private String description;
    private Float skillAmount;
    private Float rewardAmount;
    private StringBuffer condition;

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

    public PLObjectiveDTO() {
    }

    public PLObjectiveDTO(Long id, Long version, String created, String updated, String title, String description, Float skillAmount, Float rewardAmount, StringBuffer condition) {
        super(id, version, created, updated);
        this.title = title;
        this.description = description;
        this.skillAmount = skillAmount;
        this.rewardAmount = rewardAmount;
        this.condition = condition;
    }
}
