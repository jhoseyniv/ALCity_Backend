package com.alcity.dto.journey;

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


    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getUpdated() {
        return updated;
    }

    public void setUpdated(String updated) {
        this.updated = updated;
    }

    public String getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public String getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public Long getPuzzleGroupIconId() {
        return puzzleGroupIconId;
    }

    public void setPuzzleGroupIconId(Long puzzleGroupIconId) {
        this.puzzleGroupIconId = puzzleGroupIconId;
    }

    public JourneyStepDTO() {
    }

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getJourneyTitle() {
        return journeyTitle;
    }

    public void setJourneyTitle(String journeyTitle) {
        this.journeyTitle = journeyTitle;
    }

    public Long getJourneyIconId() {
        return journeyIconId;
    }

    public void setJourneyIconId(Long journeyIconId) {
        this.journeyIconId = journeyIconId;
    }

    public Long getStepId() {
        return stepId;
    }

    public void setStepId(Long stepId) {
        this.stepId = stepId;
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

    public Integer getJourneyOrdering() {
        return journeyOrdering;
    }

    public void setJourneyOrdering(Integer journeyOrdering) {
        this.journeyOrdering = journeyOrdering;
    }

    public Integer getJourneyMaxStar() {
        return journeyMaxStar;
    }

    public void setJourneyMaxStar(Integer journeyMaxStar) {
        this.journeyMaxStar = journeyMaxStar;
    }

    public Integer getJourneyMinStar() {
        return journeyMinStar;
    }

    public void setJourneyMinStar(Integer journeyMinStar) {
        this.journeyMinStar = journeyMinStar;
    }


}
