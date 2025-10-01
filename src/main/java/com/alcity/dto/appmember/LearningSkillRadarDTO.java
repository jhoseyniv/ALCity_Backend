package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class LearningSkillRadarDTO implements Serializable {
    private Long skillId;
    private Long memberId;
    private String skillTitle;
    private String skillType;
    private Long level;
    private Float amount;

    public LearningSkillRadarDTO(Long skillId, Long memberId, String skillTitle, String skillType, Long level, Float amount) {
        this.skillId = skillId;
        this.memberId = memberId;
        this.skillTitle = skillTitle;
        this.skillType = skillType;
        this.level = level;
        this.amount = amount;
    }
}
