package com.alcity.dto.puzzle;

public class PLEventDTO {
    private Long id;
    private Long puzzleLevelId;
    private Long appMemberId;
    private String eventTime;
    private String eventType;
    private Float skillAmount;
    private Float walletItemAmount;
    private String gameStatus;
    private byte[] analyticalData;

    public Long getPuzzleLevelId() {
        return puzzleLevelId;
    }

    public void setPuzzleLevelId(Long puzzleLevelId) {
        this.puzzleLevelId = puzzleLevelId;
    }

    public Long getAppMemberId() {
        return appMemberId;
    }

    public void setAppMemberId(Long appMemberId) {
        this.appMemberId = appMemberId;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public Float getSkillAmount() {
        return skillAmount;
    }

    public void setSkillAmount(Float skillAmount) {
        this.skillAmount = skillAmount;
    }

    public Float getWalletItemAmount() {
        return walletItemAmount;
    }

    public void setWalletItemAmount(Float walletItemAmount) {
        this.walletItemAmount = walletItemAmount;
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

    public PLEventDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PLEventDTO(Long id, Long puzzleLevelId, Long appMemberId, String eventTime, String eventType,
                      Float skillAmount, Float walletItemAmount, String gameStatus, byte[] analyticalData) {
        this.id = id;
        this.puzzleLevelId = puzzleLevelId;
        this.appMemberId = appMemberId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.skillAmount = skillAmount;
        this.walletItemAmount = walletItemAmount;
        this.gameStatus = gameStatus;
        this.analyticalData = analyticalData;
    }
}
