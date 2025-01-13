package com.alcity.dto.player;

public class PlayHistoryDTO {
    private Long id;
    private String playTime;
    private Integer playDuration;
    private Float playScore;
    private Integer stars;

    private Long playerId;
    private String playerUsername;

    private Long plId;
    private String plTitle;
    private String plCode;
    private Float plMaxScore;

    private Long pgId;

    private String pgTitle;

    private Long journeyId;

    private  String journeyTitle;

    private Long stepId;

    private  String stepTitle;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPlayerUsername() {
        return playerUsername;
    }

    public void setPlayerUsername(String playerUsername) {
        this.playerUsername = playerUsername;
    }

    public String getPlayTime() {
        return playTime;
    }

    public void setPlayTime(String playTime) {
        this.playTime = playTime;
    }

    public Integer getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(Integer playDuration) {
        this.playDuration = playDuration;
    }

    public Float getPlayScore() {
        return playScore;
    }

    public void setPlayScore(Float playScore) {
        this.playScore = playScore;
    }

    public Long getPlId() {
        return plId;
    }

    public void setPlId(Long plId) {
        this.plId = plId;
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

    public Float getPlMaxScore() {
        return plMaxScore;
    }

    public void setPlMaxScore(Float plMaxScore) {
        this.plMaxScore = plMaxScore;
    }

    public Long getPlayerId() {
        return playerId;
    }

    public void setPlayerId(Long playerId) {
        this.playerId = playerId;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
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

    public PlayHistoryDTO() {
    }

    public PlayHistoryDTO(Long id, String playTime, Integer playDuration, Float playScore, Integer stars, Long playerId, String playerUsername, Long plId, String plTitle, String plCode, Float plMaxScore, Long pgId, String pgTitle,
                          Long journeyId, String journeyTitle, Long stepId, String stepTitle) {
        this.id = id;
        this.playTime = playTime;
        this.playDuration = playDuration;
        this.playScore = playScore;
        this.stars = stars;
        this.playerId = playerId;
        this.playerUsername = playerUsername;
        this.plId = plId;
        this.plTitle = plTitle;
        this.plCode = plCode;
        this.plMaxScore = plMaxScore;
        this.pgId = pgId;
        this.pgTitle = pgTitle;
        this.journeyId = journeyId;
        this.journeyTitle = journeyTitle;
        this.stepId = stepId;
        this.stepTitle = stepTitle;
    }
}
