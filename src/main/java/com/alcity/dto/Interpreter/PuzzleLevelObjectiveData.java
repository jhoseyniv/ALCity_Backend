package com.alcity.dto.Interpreter;

public class PuzzleLevelObjectiveData {
    private Long id;
    private String title;
    private String description;
    private Float skillAmount;
    private Float rewardAmount;
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

    public PuzzleLevelObjectiveData() {
    }

    public PuzzleLevelObjectiveData(Long  id , String title, String description, Float skillAmount, Float rewardAmount, StringBuffer condition) {
        this.title = title;
        this.description = description;
        this.skillAmount = skillAmount;
        this.rewardAmount = rewardAmount;
        this.condition = condition;
    }
}
