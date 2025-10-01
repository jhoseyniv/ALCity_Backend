package com.alcity.dto.learning;

import com.alcity.dto.plimpexport.ruleexport.PostActionTreeExport;
import com.alcity.dto.plimpexport.rulemport.PLRulePostActionImport;
import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;
@Getter
@Setter
@NoArgsConstructor

public class LearningSkillTreeDTO<P> implements Serializable {

    private Long id;
    private String title;
    private String type;
    private String parentTitle;
    private Long parentId;
    private Float weight;
    private Long levelUpSize;
    private Long iconId;
    private String description;
    public List<LearningSkillTreeDTO<LearningSkillTreeDTO>> children;

    public LearningSkillTreeDTO<LearningSkillTreeDTO> getChild(LearningSkillTreeDTO<LearningSkillTreeDTO> child) {
        children.add(child);
        return child;
    }
    public LearningSkillTreeDTO(Long id, String title, String type, String parentTitle, Long parentId, Float weight, Long levelUpSize, Long iconId, String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.parentTitle = parentTitle;
        this.parentId = parentId;
        this.weight = weight;
        this.levelUpSize = levelUpSize;
        this.iconId = iconId;
        this.description = description;
    }
    public void setFields(Long id, String title, String type, String parentTitle, Long parentId, Float weight, Long levelUpSize, Long iconId, String description) {
        this.id = id;
        this.title = title;
        this.type = type;
        this.parentTitle = parentTitle;
        this.parentId = parentId;
        this.weight = weight;
        this.levelUpSize = levelUpSize;
        this.iconId = iconId;
        this.description = description;
    }
}
