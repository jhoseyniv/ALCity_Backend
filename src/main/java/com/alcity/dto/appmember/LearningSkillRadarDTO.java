package com.alcity.dto.appmember;

import java.io.Serializable;

public class LearningSkillRadarDTO implements Serializable {
    private Long skillId;
    private Long memberId;
    private String skillTitle;
    private String skillType;
    private Long level;
    private Float amount;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

    public Long getLevel() {
        return level;
    }

    public void setLevel(Long level) {
        this.level = level;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public LearningSkillRadarDTO() {
    }

    public LearningSkillRadarDTO(Long skillId, Long memberId, String skillTitle, String skillType, Long level, Float amount) {
        this.skillId = skillId;
        this.memberId = memberId;
        this.skillTitle = skillTitle;
        this.skillType = skillType;
        this.level = level;
        this.amount = amount;
    }
}
