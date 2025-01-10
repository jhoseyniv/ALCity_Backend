package com.alcity.dto.player;

public class PlayHistoryDTO {
    private Long id;
    private String playTime;
    private Integer playDuration;
    private Float playScore;

    private Long playerId;
    private String playerUsername;

    private Long plId;
    private String plTitle;
    private String plCode;
    private Float plMaxScore;

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

    public PlayHistoryDTO() {
    }

    public PlayHistoryDTO(Long id, String playerUsername, String playTime,
                          Integer playDuration, Float playScore, Long plId, String plTitle, String plCode, Float plMaxScore) {
        this.id = id;
        this.playerUsername = playerUsername;
        this.playTime = playTime;
        this.playDuration = playDuration;
        this.playScore = playScore;
        this.plId = plId;
        this.plTitle = plTitle;
        this.plCode = plCode;
        this.plMaxScore = plMaxScore;
    }
}
