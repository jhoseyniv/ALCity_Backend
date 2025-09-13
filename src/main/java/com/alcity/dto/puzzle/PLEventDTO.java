package com.alcity.dto.puzzle;

public class PLEventDTO {
    private Long id;
    private Long puzzleLevelId;
    private Long appMemberId;
    private String eventTime;
    private String eventType;
    private Float skillAmount;
    private Float walletItemAmount;
    private Integer gameStatusId;
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

    public Integer getGameStatusId() {
        return gameStatusId;
    }

    public void setGameStatusId(Integer gameStatusId) {
        this.gameStatusId = gameStatusId;
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
                      Float skillAmount, Float walletItemAmount, Integer gameStatusId, byte[] analyticalData) {
        this.id = id;
        this.puzzleLevelId = puzzleLevelId;
        this.appMemberId = appMemberId;
        this.eventTime = eventTime;
        this.eventType = eventType;
        this.skillAmount = skillAmount;
        this.walletItemAmount = walletItemAmount;
        this.gameStatusId = gameStatusId;
        this.analyticalData = analyticalData;
    }
}
