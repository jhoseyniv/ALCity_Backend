package com.alcity.dtotransient;

public class AppMemberStepInfo {

    private Long stepId;
    private String stepTitle;

    private Integer xPos;
    private Integer yPos;
    private Long puzzleLevelId;
    private String puzzleLevelTitle;

    private Long puzzleGroupId;
    private String puzzleGroupTitle;

    private Integer stars;

    private Boolean isCompleted ;

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public void setCompleted(Boolean completed) {
        isCompleted = completed;
    }

    public Long getStepId() {
        return stepId;
    }

    public Integer getxPos() {
        return xPos;
    }

    public void setxPos(Integer xPos) {
        this.xPos = xPos;
    }

    public Integer getyPos() {
        return yPos;
    }

    public void setyPos(Integer yPos) {
        this.yPos = yPos;
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

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public String getPuzzleLevelTitle() {
        return puzzleLevelTitle;
    }

    public void setPuzzleLevelTitle(String puzzleLevelTitle) {
        this.puzzleLevelTitle = puzzleLevelTitle;
    }

    public Long getPuzzleGroupId() {
        return puzzleGroupId;
    }

    public void setPuzzleGroupId(Long puzzleGroupId) {
        this.puzzleGroupId = puzzleGroupId;
    }

    public String getPuzzleGroupTitle() {
        return puzzleGroupTitle;
    }

    public void setPuzzleGroupTitle(String puzzleGroupTitle) {
        this.puzzleGroupTitle = puzzleGroupTitle;
    }

    public AppMemberStepInfo() {
    }

    public AppMemberStepInfo(Long stepId, String stepTitle,Integer xPos,Integer yPos, Long puzzleLevelId, String puzzleLevelTitle,
                             Long puzzleGroupId, String puzzleGroupTitle,Integer stars,Boolean isCompleted) {
        this.stepId = stepId;
        this.stepTitle = stepTitle;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.puzzleGroupId = puzzleGroupId;
        this.puzzleGroupTitle = puzzleGroupTitle;
        this.stars = stars;
        this.xPos =xPos;
        this.yPos =yPos;
        this.isCompleted = isCompleted;
    }
}
