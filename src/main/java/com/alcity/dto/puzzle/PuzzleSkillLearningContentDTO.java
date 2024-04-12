package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningSkillDTO;
import com.alcity.entity.learning.LearningSkill;

public class PuzzleSkillLearningContentDTO extends BaseTableDTO {
    private LearningSkillDTO learningSkillDTO;
    private LearningContentDTO learningContentDTO;

    public PuzzleSkillLearningContentDTO() {
    }

    public PuzzleSkillLearningContentDTO(Long id, Long version, String created, String updated, LearningSkillDTO learningSkillDTO, LearningContentDTO learningContentDTO) {
        super(id, version, created, updated);
        this.learningSkillDTO = learningSkillDTO;
        this.learningContentDTO = learningContentDTO;
    }

    public LearningSkillDTO getLearningSkillDTO() {
        return learningSkillDTO;
    }

    public void setLearningSkillDTO(LearningSkillDTO learningSkillDTO) {
        this.learningSkillDTO = learningSkillDTO;
    }

    public LearningContentDTO getLearningContentDTO() {
        return learningContentDTO;
    }

    public void setLearningContentDTO(LearningContentDTO learningContentDTO) {
        this.learningContentDTO = learningContentDTO;
    }
}
