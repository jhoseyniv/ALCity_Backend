package com.alcity.dto.puzzle;

import com.alcity.dto.base.BaseTableDTO;
import com.alcity.dto.learning.LearningContentDTO;
import com.alcity.dto.learning.LearningTopicDTO;
import com.alcity.entity.learning.LearningContent;
import com.alcity.entity.learning.LearningTopic;
import com.alcity.entity.puzzle.PuzzleLevel;

public class PuzzleLevel_LearningTopicDTO extends BaseTableDTO {
    private PuzzleLevelDTO puzzleLevelDTO;
    private LearningTopicDTO learningTopicDTO;
    private LearningContentDTO learningContentDTO;

    public PuzzleLevelDTO getPuzzleLevelDTO() {
        return puzzleLevelDTO;
    }

    public void setPuzzleLevelDTO(PuzzleLevelDTO puzzleLevelDTO) {
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

    public PuzzleLevel_LearningTopicDTO(Long id, Long version, String created, String updated, PuzzleLevelDTO puzzleLevelDTO, LearningTopicDTO learningTopicDTO, LearningContentDTO learningContentDTO) {
        super(id, version, created, updated);
        this.puzzleLevelDTO = puzzleLevelDTO;
        this.learningTopicDTO = learningTopicDTO;
        this.learningContentDTO = learningContentDTO;
    }

}
