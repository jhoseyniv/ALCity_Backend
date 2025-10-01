package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
public class AppMemberSkillScoreDTO implements Serializable {
    Long skillId;
    String skillTitle;
    String skillDescription;
    Long skillLevel;
    String skillType;
    Float amount;
    Float weight;
    Long appMemberId;
    Long skillIconId;

    public AppMemberSkillScoreDTO(Long skillId, String skillTitle, String skillDescription,
                                  Long skillLevel, String skillType, Float amount,Float weight, Long appMemberId, Long skillIconId) {
        this.skillId = skillId;
        this.skillTitle = skillTitle;
        this.skillDescription = skillDescription;
        this.skillLevel = skillLevel;
        this.skillType = skillType;
        this.amount = amount;
        this.weight = weight;
        this.appMemberId = appMemberId;
        this.skillIconId = skillIconId;
    }
}
