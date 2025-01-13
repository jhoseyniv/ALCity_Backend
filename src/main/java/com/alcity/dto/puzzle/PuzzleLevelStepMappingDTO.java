package com.alcity.dto.puzzle;

import com.alcity.dto.journey.JourneyStepDTO;
import com.alcity.entity.journey.JourneyStep;

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

    public PuzzleLevelStepMappingDTO() {
    }

    public Long getPlId() {
        return plId;
    }

    public void setPlId(Long plId) {
        this.plId = plId;
    }

    public String getPlApproveDate() {
        return plApproveDate;
    }

    public void setPlApproveDate(String plApproveDate) {
        this.plApproveDate = plApproveDate;
    }

    public Long getPlOrdering() {
        return plOrdering;
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public void setPlOrdering(Long plOrdering) {
        this.plOrdering = plOrdering;
    }

    public String getPlTitle() {
        return plTitle;
    }

    public void setPlTitle(String plTitle) {
        this.plTitle = plTitle;
    }

    public String getPlCode() {
        return plCode;
    }

    public void setPlCode(String plCode) {
        this.plCode = plCode;
    }

    public Integer getPlFromAge() {
        return plFromAge;
    }

    public void setPlFromAge(Integer plFromAge) {
        this.plFromAge = plFromAge;
    }

    public Integer getPlToAge() {
        return plToAge;
    }

    public void setPlToAge(Integer plToAge) {
        this.plToAge = plToAge;
    }

    public Float getPlMaxScore() {
        return plMaxScore;
    }

    public void setPlMaxScore(Float plMaxScore) {
        this.plMaxScore = plMaxScore;
    }

    public Float getFirstStarScore() {
        return firstStarScore;
    }

    public void setFirstStarScore(Float firstStarScore) {
        this.firstStarScore = firstStarScore;
    }

    public Float getSecondStarScore() {
        return secondStarScore;
    }

    public void setSecondStarScore(Float secondStarScore) {
        this.secondStarScore = secondStarScore;
    }

    public Float getThirdStartScore() {
        return thirdStartScore;
    }

    public void setThirdStartScore(Float thirdStartScore) {
        this.thirdStartScore = thirdStartScore;
    }

    public Long getPgId() {
        return pgId;
    }

    public void setPgId(Long pgId) {
        this.pgId = pgId;
    }

    public String getPgTitle() {
        return pgTitle;
    }

    public void setPgTitle(String pgTitle) {
        this.pgTitle = pgTitle;
    }

    public String getStepTitle() {
        return stepTitle;
    }

    public void setStepTitle(String stepTitle) {
        this.stepTitle = stepTitle;
    }

    public Integer getStepOrdering() {
        return stepOrdering;
    }

    public void setStepOrdering(Integer stepOrdering) {
        this.stepOrdering = stepOrdering;
    }

    public Integer getStepXpos() {
        return stepXpos;
    }

    public void setStepXpos(Integer stepXpos) {
        this.stepXpos = stepXpos;
    }

    public Integer getStepYpos() {
        return stepYpos;
    }

    public void setStepYpos(Integer stepYpos) {
        this.stepYpos = stepYpos;
    }

    public String getJourneyTitle() {
        return journeyTitle;
    }

    public void setJourneyTitle(String journeyTitle) {
        this.journeyTitle = journeyTitle;
    }

    public Integer getJourneyOrdering() {
        return journeyOrdering;
    }

    public void setJourneyOrdering(Integer journeyOrdering) {
        this.journeyOrdering = journeyOrdering;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
    }

    public PuzzleLevelStepMappingDTO(Long plId, String plApproveDate, Long plOrdering, String plTitle, String plCode, Integer plFromAge, Integer plToAge, Float plMaxScore, Float firstStarScore, Float secondStarScore, Float thirdStartScore, Long pgId, String pgTitle,
                                     Long stepId,String stepTitle, Integer stepOrdering, Integer stepXpos, Integer stepYpos, String journeyTitle, Integer journeyOrdering) {
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
    }
}
