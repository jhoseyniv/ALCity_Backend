package com.alcity.dto.journey;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor

public class JourneyStepDTO  {
    private Long stepId;
    private String stepTitle;
    private Integer stepOrdering;
    private Integer stepXpos;
    private Integer stepYpos;

    private Long journeyId;
    private String journeyTitle;
    private Integer journeyOrdering;
    private Integer journeyMaxStar;
    private Integer journeyMinStar;

    private String puzzleGroupTitle;
    private Long puzzleGroupId;
    private Long puzzleGroupIconId;
    private Long journeyIconId;

    private Long version;
    private String created;
    private String updated;
    private String createdBy;

    private String updatedBy;

    public JourneyStepDTO(Long stepId, String stepTitle, Integer stepOrdering, Integer stepXpos, Integer stepYpos,
                          Long journeyId, String journeyTitle, Integer journeyOrdering, Integer journeyMaxStar, Integer journeyMinStar, String puzzleGroupTitle, Long puzzleGroupId, Long puzzleGroupIconId, Long journeyIconId,
                          Long version, String created, String updated, String createdBy, String updatedBy) {
        this.stepId = stepId;
        this.stepTitle = stepTitle;
        this.stepOrdering = stepOrdering;
        this.stepXpos = stepXpos;
        this.stepYpos = stepYpos;
        this.journeyId = journeyId;
        this.journeyTitle = journeyTitle;
        this.journeyOrdering = journeyOrdering;
        this.journeyMaxStar = journeyMaxStar;
        this.journeyMinStar = journeyMinStar;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleGroupIconId = puzzleGroupIconId;
        this.journeyIconId = journeyIconId;
        this.version = version;
        this.created = created;
        this.updated = updated;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }
}
