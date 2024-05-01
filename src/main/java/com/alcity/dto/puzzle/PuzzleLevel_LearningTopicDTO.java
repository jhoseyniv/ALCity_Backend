package com.alcity.dto.puzzle;

import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;

public class PuzzleLevel_LearningTopicDTO {
    private Long id;
    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    private PLDTO puzzleLevelDTO;
    private LearningTopicDTO learningTopicDTO;
    private LearningContentDTO learningContentDTO;

    public PLDTO getPuzzleLevelDTO() {
        return puzzleLevelDTO;
    }

    public void setPuzzleLevelDTO(PLDTO puzzleLevelDTO) {
        this.puzzleLevelDTO = puzzleLevelDTO;
    }

    public LearningTopicDTO getLearningTopicDTO() {
        return learningTopicDTO;
    }

    public void setLearningTopicDTO(LearningTopicDTO learningTopicDTO) {
        this.learningTopicDTO = learningTopicDTO;
    }

    public LearningContentDTO getLearningContentDTO() {
        return learningContentDTO;
    }

    public void setLearningContentDTO(LearningContentDTO learningContentDTO) {
        this.learningContentDTO = learningContentDTO;
    }

    public PuzzleLevel_LearningTopicDTO() {
    }

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

    public PuzzleLevel_LearningTopicDTO(Long id, Long version, String created, String updated, String createdBy, String updatedBy) {
        this.id = id;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
