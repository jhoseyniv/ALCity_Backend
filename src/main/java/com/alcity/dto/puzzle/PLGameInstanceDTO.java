package com.alcity.dto.puzzle;

public class PLGameInstanceDTO {

    Long id;
    Long appMemmberId;
    String userName;
    Long puzzleLevelId;
    String puzzleLevelTitle;
    String startPlayTime;
    String gameStatus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAppMemmberId() {
        return appMemmberId;
    }

    public void setAppMemmberId(Long appMemmberId) {
        this.appMemmberId = appMemmberId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
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

    public String getStartPlayTime() {
        return startPlayTime;
    }

    public void setStartPlayTime(String startPlayTime) {
        this.startPlayTime = startPlayTime;
    }

    public String getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(String gameStatus) {
        this.gameStatus = gameStatus;
    }

    public PLGameInstanceDTO() {
    }

    public PLGameInstanceDTO(Long id, Long appMemmberId, String userName, Long puzzleLevelId, String puzzleLevelTitle, String startPlayTime, String gameStatus) {
        this.id = id;
        this.appMemmberId = appMemmberId;
        this.userName = userName;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.startPlayTime = startPlayTime;
        this.gameStatus = gameStatus;
    }
}
