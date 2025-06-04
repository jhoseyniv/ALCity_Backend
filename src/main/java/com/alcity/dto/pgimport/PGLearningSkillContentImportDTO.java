package com.alcity.dto.pgimport;

public class PGLearningSkillContentImportDTO {
    private Long learningSkillId;
    private Long learningContentId;

    public Long getLearningSkillId() {
        return learningSkillId;
    }

    public void setLearningSkillId(Long learningSkillId) {
        this.learningSkillId = learningSkillId;
    }

    public Long getLearningContentId() {
        return learningContentId;
    }

    public void setLearningContentId(Long learningContentId) {
        this.learningContentId = learningContentId;
    }

    public PGLearningSkillContentImportDTO(Long learningSkillId, Long learningContentId) {
        this.learningSkillId = learningSkillId;
        this.learningContentId = learningContentId;
    }
}
