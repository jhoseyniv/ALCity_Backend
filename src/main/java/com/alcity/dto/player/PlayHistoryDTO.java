package com.alcity.dto.player;

public class PlayHistoryDTO {
    private Long id;
    private String startPlayTime;

    private String endPlayTime;
    private Long playDuration;
    private Float playScore;
    private Integer stars;

    private Long playerId;
    private String playerUsername;

    private Long plId;
    private Integer plFromAge;

    private Integer plToAge;
    private String plTitle;
    private String plCode;
    private Float plMaxScore;

    private Long pgId;

    private String pgTitle;

    private Long journeyId;

    private  String journeyTitle;

    private Long stepId;

    private  String stepTitle;

    private  StringBuffer analyticalData;


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

    public StringBuffer getAnalyticalData() {
        return analyticalData;
    }

    public void setAnalyticalData(StringBuffer analyticalData) {
        this.analyticalData = analyticalData;
    }

    public PlayHistoryDTO() {
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

    public String getStartPlayTime() {
        return startPlayTime;
    }

    public void setStartPlayTime(String startPlayTime) {
        this.startPlayTime = startPlayTime;
    }

    public String getEndPlayTime() {
        return endPlayTime;
    }

    public void setEndPlayTime(String endPlayTime) {
        this.endPlayTime = endPlayTime;
    }

    public Long getPlayDuration() {
        return playDuration;
    }

    public void setPlayDuration(Long playDuration) {
        this.playDuration = playDuration;
    }

    public PlayHistoryDTO(Long id, String startPlayTime, String endPlayTime, Long playDuration, Float playScore, Integer stars, Long playerId, String playerUsername, Long plId, String plTitle, String plCode, Integer plFromAge, Integer plToAge, Float plMaxScore, Long pgId, String pgTitle,
                          Long journeyId, String journeyTitle, Long stepId, String stepTitle,StringBuffer analyticalData) {
        this.id = id;
        this.startPlayTime = startPlayTime;
        this.endPlayTime = endPlayTime;
        this.playDuration = playDuration;
        this.playScore = playScore;
        this.stars = stars;
        this.playerId = playerId;
        this.playerUsername = playerUsername;
        this.plId = plId;
        this.plTitle = plTitle;
        this.plCode = plCode;
        this.plFromAge = plFromAge;
        this.plToAge = plToAge;
        this.plMaxScore = plMaxScore;
        this.pgId = pgId;
        this.pgTitle = pgTitle;
        this.journeyId = journeyId;
        this.journeyTitle = journeyTitle;
        this.stepId = stepId;
        this.stepTitle = stepTitle;
        this.analyticalData = analyticalData;
    }
}
