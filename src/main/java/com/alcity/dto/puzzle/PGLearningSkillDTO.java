package com.alcity.dto.puzzle;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PGLearningSkillDTO {

    private Long id;

    private String learningSkillTitle;
    private Long learningSkillId;

    private String puzzleGroupTitle;
    private Long puzzleGroupId;
    private Long learningContentId;
    private String learningContentDescText;

    public PGLearningSkillDTO(Long id , String learningSkillTitle,
                              Long learningSkillId, String puzzleGroupTitle, Long puzzleGroupId, Long learningContentId, String learningContentDescText) {
        this.id = id;
        this.learningSkillTitle = learningSkillTitle;
        this.learningSkillId = learningSkillId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.learningContentId = learningContentId;
        this.learningContentDescText = learningContentDescText;
    }
}
