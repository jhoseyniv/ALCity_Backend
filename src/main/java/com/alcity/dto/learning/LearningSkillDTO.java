package com.alcity.dto.learning;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class LearningSkillDTO {
    private Long id;
    private String title;
    private String type;
    private String parentTitle;
    private Long parentId;
    private Float weight;
    private Long levelUpSize;
    private Long iconId;
    private String description;

    public LearningSkillDTO(Long id, String title, String type, Long parentId,String parentTitle,Long levelUpSize,Long iconId,Float weight,String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.parentId = parentId;
        this.parentTitle = parentTitle;
        this.levelUpSize = levelUpSize;
        this.iconId = iconId;
        this.weight = weight;
        this.description = description;
    }
}
