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
    public String toString() {
        return "id: '" + this.id + "', title: '" + this.title + "', type: '" + this.type + "'"+
                "', parentId: '" + this.parentId + "'" + "', parentTitle: '" + this.parentTitle + "'"
                + "', levelUpSize: '" + this.levelUpSize + "'"
                + "', iconId: '" + this.iconId + "'"
                + "', weight: '" + this.weight + "'"
                + "', description: '" + this.description + "'"
                ;
    }

}
