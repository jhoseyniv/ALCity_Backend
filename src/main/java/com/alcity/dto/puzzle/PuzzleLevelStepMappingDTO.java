package com.alcity.dto.puzzle;

import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.entity.journey.JourneyStep;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PuzzleLevelStepMappingDTO {
    private Long plId;
    private String plApproveDate;
    private Long plOrdering;
    private String plTitle;
    private String plCode;
    private Integer plFromAge;
    private Integer plToAge;
    private Float plMaxScore;
    private Float firstStarScore;
    private Float secondStarScore;
    private Float thirdStartScore;
    private Long pgId;
    private String pgTitle;

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

    public PuzzleLevelStepMappingDTO(Long plId, String plApproveDate, Long plOrdering, String plTitle, String plCode, Integer plFromAge, Integer plToAge, Float plMaxScore, Float firstStarScore, Float secondStarScore, Float thirdStartScore, Long pgId, String pgTitle,
                                     Long stepId,String stepTitle, Integer stepOrdering, Integer stepXpos, Integer stepYpos, String journeyTitle, Integer journeyOrdering,Integer journeyMaxStar, Integer journeyMinStar) {
        this.plId = plId;
        this.plApproveDate = plApproveDate;
        this.plOrdering = plOrdering;
        this.plTitle = plTitle;
        this.plCode = plCode;
        this.plFromAge = plFromAge;
        this.plToAge = plToAge;
        this.plMaxScore = plMaxScore;
        this.firstStarScore = firstStarScore;
        this.secondStarScore = secondStarScore;
        this.thirdStartScore = thirdStartScore;
        this.pgId = pgId;
        this.pgTitle = pgTitle;

        this.stepId = stepId;
        this.stepTitle = stepTitle;
        this.stepOrdering = stepOrdering;
        this.stepXpos = stepXpos;
        this.stepYpos = stepYpos;
        this.journeyTitle = journeyTitle;
        this.journeyOrdering = journeyOrdering;
        this.journeyMaxStar = journeyMaxStar;
        this.journeyMinStar = journeyMinStar;
    }
}
