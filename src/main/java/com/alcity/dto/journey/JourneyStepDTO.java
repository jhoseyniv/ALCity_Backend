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



}
