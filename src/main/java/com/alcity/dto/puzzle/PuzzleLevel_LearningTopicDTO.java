package com.alcity.dto.puzzle;

import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;

public class PuzzleLevel_LearningTopicDTO extends BaseTableDTO {
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

    public PuzzleLevel_LearningTopicDTO(Long id, Long version, String created, String updated, PLDTO puzzleLevelDTO, LearningTopicDTO learningTopicDTO, LearningContentDTO learningContentDTO) {
        super(id, version, created, updated);
        this.puzzleLevelDTO = puzzleLevelDTO;
        this.learningTopicDTO = learningTopicDTO;
        this.learningContentDTO = learningContentDTO;
    }

}
