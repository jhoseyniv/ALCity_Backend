package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class PlLearningTopicDTO {
    private Long id;
    private Long puzzleLevelId;
    private String puzzleLevelTitle;
    private Long learningTopicId;
    private String learningTopicTitle;
    private Long learningContentId;
    private String learningContentDescText;
    private String learningContentDescBrief;
    private String learningContentFileName;
    private Long binaryContentId;

    public PlLearningTopicDTO(Long id, Long puzzleLevelId, String puzzleLevelTitle, Long learningTopicId, String learningTopicTitle,
                              Long learningContentId, String learningContentDescText, String learningContentDescBrief, String learningContentFileName, Long binaryContentId) {
        this.id = id;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.learningTopicId = learningTopicId;
        this.learningTopicTitle = learningTopicTitle;
        this.learningContentId = learningContentId;
        this.learningContentDescText = learningContentDescText;
        this.learningContentDescBrief = learningContentDescBrief;
        this.learningContentFileName = learningContentFileName;
        this.binaryContentId = binaryContentId;
    }
}
