package com.alcity.dto.learning;

import com.alcity.dto.plimpexport.ruleexport.PostActionTreeExport;
import com.alcity.dto.plimpexport.rulemport.PLRulePostActionImport;
import com.alcity.dto.plimpexport.rulemport.PostActionTreeImport;

import java.io.Serializable;
import java.util.List;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Float getWeight() {
        return weight;
    }

    public void setWeight(Float weight) {
        this.weight = weight;
    }

    public Long getLevelUpSize() {
        return levelUpSize;
    }

    public void setLevelUpSize(Long levelUpSize) {
        this.levelUpSize = levelUpSize;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<LearningSkillTreeDTO<LearningSkillTreeDTO>> getChildren() {
        return children;
    }

    public void setChildren(List<LearningSkillTreeDTO<LearningSkillTreeDTO>> children) {
        this.children = children;
    }

    public LearningSkillTreeDTO() {
    }
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
