package com.alcity.dto.learning;

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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParentTitle() {
        return parentTitle;
    }

    public void setParentTitle(String parentTitle) {
        this.parentTitle = parentTitle;
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

    public LearningSkillDTO() {
    }

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
