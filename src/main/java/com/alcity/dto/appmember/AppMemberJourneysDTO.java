package com.alcity.dto.appmember;

public class AppMemberJourneysDTO {
    private Long journeyId;
    private String title;
    private Boolean isOpen;
    private Long  iconId;
    private Integer  maxScore;
    private Integer  minScore;
    private Integer  currentScore;
    private Long appMemberId;

    public Long getJourneyId() {
        return journeyId;
    }

    public void setJourneyId(Long journeyId) {
        this.journeyId = journeyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Boolean getOpen() {
        return isOpen;
    }

    public void setOpen(Boolean open) {
        isOpen = open;
    }

    public Long getIconId() {
        return iconId;
    }

    public void setIconId(Long iconId) {
        this.iconId = iconId;
    }

    public Integer getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Integer maxScore) {
        this.maxScore = maxScore;
    }

    public Integer getMinScore() {
        return minScore;
    }

    public void setMinScore(Integer minScore) {
        this.minScore = minScore;
    }

    public Integer getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(Integer currentScore) {
        this.currentScore = currentScore;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public AppMemberJourneysDTO(Long journeyId, String title, Boolean isOpen, Long iconId, Integer maxScore, Integer minScore, Integer currentScore, Long appMemberId) {
        this.journeyId = journeyId;
        this.title = title;
        this.isOpen = isOpen;
        this.iconId = iconId;
        this.maxScore = maxScore;
        this.minScore = minScore;
        this.currentScore = currentScore;
        this.appMemberId = appMemberId;
    }
}
