package com.alcity.entity.appmember;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class AppMemberStepInfo {

    private Long stepId;
    private String stepTitle;

    private Integer xPos;
    private Integer yPos;

    private Integer ordering;
    private Long puzzleLevelId;
    private Long puzzleLevelIconId;
    private String puzzleLevelTitle;

    private Long puzzleGroupId;
    private String puzzleGroupTitle;

    private Integer stars;

    private Boolean completed ;

    public AppMemberStepInfo(Long stepId, String stepTitle,Integer xPos,Integer yPos,Integer ordering, Long puzzleLevelId,Long puzzleLevelIconId, String puzzleLevelTitle,
                             Long puzzleGroupId, String puzzleGroupTitle,Integer stars,Boolean completed) {
        this.stepId = stepId;
        this.stepTitle = stepTitle;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleLevelIconId = puzzleLevelIconId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.stars = stars;
        this.xPos =xPos;
        this.yPos =yPos;
        this.ordering = ordering;
        this.completed = completed;
    }
}
