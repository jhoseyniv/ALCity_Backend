package com.alcity.dto.puzzle;

public class PLCopyDTO {
    private String title;
    private String code;
    private Integer fromAge;
    private Integer toAge;
    private  Long puzzleLevelId;

    private  Boolean isPLGround;
    private  Boolean isObjectives;
    private  Boolean isInstances;
    private  Boolean isVariables;
    private  Boolean isRules;
    private  Boolean isLearningTopics;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getFromAge() {
        return fromAge;
    }

    public void setFromAge(Integer fromAge) {
        this.fromAge = fromAge;
    }

    public Integer getToAge() {
        return toAge;
    }

    public void setToAge(Integer toAge) {
        this.toAge = toAge;
    }

    public Boolean getPLGround() {
        return isPLGround;
    }

    public void setPLGround(Boolean PLGround) {
        isPLGround = PLGround;
    }

    public Boolean getObjectives() {
        return isObjectives;
    }

    public void setObjectives(Boolean objectives) {
        isObjectives = objectives;
    }

    public Boolean getInstances() {
        return isInstances;
    }

    public void setInstances(Boolean instances) {
        isInstances = instances;
    }

    public Boolean getVariables() {
        return isVariables;
    }

    public void setVariables(Boolean variables) {
        isVariables = variables;
    }

    public Boolean getRules() {
        return isRules;
    }

    public void setRules(Boolean rules) {
        isRules = rules;
    }

    public Boolean getLearningTopics() {
        return isLearningTopics;
    }

    public void setLearningTopics(Boolean learningTopics) {
        isLearningTopics = learningTopics;
    }

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public PLCopyDTO(String title, String code, Integer fromAge, Integer toAge, Long puzzleLevelId,
                     Boolean isPLGround, Boolean isObjectives, Boolean isInstances, Boolean isVariables,
                     Boolean isRules, Boolean isLearningTopics) {
        this.title = title;
        this.code = code;
        this.fromAge = fromAge;
        this.toAge = toAge;
        this.puzzleLevelId = puzzleLevelId;
        this.isPLGround = isPLGround;
        this.isObjectives = isObjectives;
        this.isInstances = isInstances;
        this.isVariables = isVariables;
        this.isRules = isRules;
        this.isLearningTopics = isLearningTopics;
    }

    public PLCopyDTO() {
    }
}
