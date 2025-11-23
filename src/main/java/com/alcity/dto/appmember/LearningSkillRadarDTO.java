package com.alcity.dto.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
@NoArgsConstructor

public class LearningSkillRadarDTO implements Serializable {

    private Long id;
    private Long skillId;
    private String skillTitle;
    private String skillType;
    private Long level;
    private Float amount;

    public LearningSkillRadarDTO(Long id,Long skillId,  String skillTitle, String skillType, Long level, Float amount) {
        this.skillId = skillId;
        this.id = id;
        this.skillTitle = skillTitle;
        this.skillType = skillType;
        this.level = level;
        this.amount = amount;
    }
}
