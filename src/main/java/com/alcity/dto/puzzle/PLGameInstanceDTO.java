package com.alcity.dto.puzzle;

import com.alcity.entity.appmember.WalletItem;
import com.alcity.entity.learning.LearningSkill;

public class PLGameInstanceDTO {

    Long id;
    Long appMemmberId;
    String userName;
    Long puzzleLevelId;
    String puzzleLevelTitle;
    String startPlayTime;
    String gameStatus;
    private byte[] analyticalData;
    private Integer stars;
    private Long learningSkillId;
    private Float skillAmount;
    private Long walletItemId;
    private Float walletItemAmount;


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

    public byte[] getAnalyticalData() {
        return analyticalData;
    }

    public void setAnalyticalData(byte[] analyticalData) {
        this.analyticalData = analyticalData;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Long getLearningSkillId() {
        return learningSkillId;
    }

    public void setLearningSkillId(Long learningSkillId) {
        this.learningSkillId = learningSkillId;
    }

    public Float getSkillAmount() {
        return skillAmount;
    }

    public void setSkillAmount(Float skillAmount) {
        this.skillAmount = skillAmount;
    }

    public Long getWalletItemId() {
        return walletItemId;
    }

    public void setWalletItemId(Long walletItemId) {
        this.walletItemId = walletItemId;
    }

    public Float getWalletItemAmount() {
        return walletItemAmount;
    }

    public void setWalletItemAmount(Float walletItemAmount) {
        this.walletItemAmount = walletItemAmount;
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
