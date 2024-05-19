package com.alcity.dto.puzzle;

import com.alcity.dto.base.LearningSkillDTO;
import com.alcity.dto.learning.LearningContentDTO;

public class LearningSkillLContentDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;
    private Long createdById;

    private Long updatedById;

    public Long getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Long createdById) {
        this.createdById = createdById;
    }

    public Long getUpdatedById() {
        return updatedById;
    }

    public void setUpdatedById(Long updatedById) {
        this.updatedById = updatedById;
    }

    private String learningSkillTitle;
    private Long learningSkillId;

    private String puzzleGroupTitle;
    private Long puzzleGroupId;
    private Long learningContentId;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getLearningSkillTitle() {
        return learningSkillTitle;
    }

    public void setLearningSkillTitle(String learningSkillTitle) {
        this.learningSkillTitle = learningSkillTitle;
    }

    public Long getLearningSkillId() {
        return learningSkillId;
    }

    public void setLearningSkillId(Long learningSkillId) {
        this.learningSkillId = learningSkillId;
    }

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getLearningContentId() {
        return learningContentId;
    }

    public void setLearningContentId(Long learningContentId) {
        this.learningContentId = learningContentId;
    }

    public LearningSkillLContentDTO() {
    }

    public LearningSkillLContentDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy, Long createdById, Long updatedById, String learningSkillTitle,
                                    Long learningSkillId, String puzzleGroupTitle, Long puzzleGroupId, Long learningContentId) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
        this.createdById = createdById;
        this.updatedById = updatedById;
        this.learningSkillTitle = learningSkillTitle;
        this.learningSkillId = learningSkillId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.learningContentId = learningContentId;
    }
}
