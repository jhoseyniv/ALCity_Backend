package com.alcity.dto.puzzle;

import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.learning.LearningSkill;

public class PLGameInstanceDTO {

    Long id;
    Long appMemmberId;
    Long puzzleLevelId;
    String puzzleLevelTitle;
    String startPlayTime;
    String endPlayTime;
    String gameStatus;
    private byte[] analyticalData;


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

    public byte[] getAnalyticalData() {
        return analyticalData;
    }

    public void setAnalyticalData(byte[] analyticalData) {
        this.analyticalData = analyticalData;
    }


    public PLGameInstanceDTO() {
    }

    public String getEndPlayTime() {
        return endPlayTime;
    }

    public void setEndPlayTime(String endPlayTime) {
        this.endPlayTime = endPlayTime;
    }

    public PLGameInstanceDTO(Long id, Long appMemmberId, Long puzzleLevelId, String puzzleLevelTitle, String startPlayTime, String endPlayTime, String gameStatus, byte[] analyticalData) {
        this.id = id;
        this.appMemmberId = appMemmberId;
        this.puzzleLevelId = puzzleLevelId;
        this.puzzleLevelTitle = puzzleLevelTitle;
        this.startPlayTime = startPlayTime;
        this.endPlayTime = endPlayTime;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
    }
}
