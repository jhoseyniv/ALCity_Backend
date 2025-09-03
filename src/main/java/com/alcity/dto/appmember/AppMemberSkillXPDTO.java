package com.alcity.dto.appmember;

import java.io.Serializable;

public class AppMemberSkillXPDTO implements Serializable {
    Long skillId;
    String skillTitle;
    String skillDescription;
    Long skillLevel;
    String skillType;
    Float amount;
    Long appMemberId;
    Long skillIconId;

    public Long getSkillId() {
        return skillId;
    }

    public void setSkillId(Long skillId) {
        this.skillId = skillId;
    }

    public String getSkillTitle() {
        return skillTitle;
    }

    public void setSkillTitle(String skillTitle) {
        this.skillTitle = skillTitle;
    }

    public String getSkillDescription() {
        return skillDescription;
    }

    public void setSkillDescription(String skillDescription) {
        this.skillDescription = skillDescription;
    }

    public Long getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(Long skillLevel) {
        this.skillLevel = skillLevel;
    }

    public String getSkillType() {
        return skillType;
    }

    public void setSkillType(String skillType) {
        this.skillType = skillType;
    }

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

    public Long getSkillIconId() {
        return skillIconId;
    }

    public void setSkillIconId(Long skillIconId) {
        this.skillIconId = skillIconId;
    }

    public AppMemberSkillXPDTO() {
    }

    public AppMemberSkillXPDTO(Long skillId, String skillTitle, String skillDescription,
                               Long skillLevel, String skillType, Float amount, Long appMemberId, Long skillIconId) {
        this.skillId = skillId;
        this.skillTitle = skillTitle;
        this.skillDescription = skillDescription;
        this.skillLevel = skillLevel;
        this.skillType = skillType;
        this.amount = amount;
        this.appMemberId = appMemberId;
        this.skillIconId = skillIconId;
    }
}
